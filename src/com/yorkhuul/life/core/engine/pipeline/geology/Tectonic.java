package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.shape.DivideMapShape;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddEffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.Line;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.utils.position.*;
import com.yorkhuul.life.core.world.region.Region;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.tile.TileWithCoordinates;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.utils.random.RandomInteger;
import com.yorkhuul.life.utils.random.RandomSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tectonic implements GenerationStep<TectonicConfig> {

    public Tectonic() {}


    // Setters


    public void setType(String type) {
        if (!Objects.equals(type, "rift") && !Objects.equals(type, "subduction")) {
            type = "subduction";
        }
        //this.type = type;
    }


    //Methods
    @Override
    public void apply(World world, TectonicConfig config) {
        TileWithCoordinates startingTile;
        for (int i = 0; i < config.getCount(); i++) {
            int x = new RandomInteger(0, world.getWidth()).getRandomInt();
            int y = new RandomInteger(0, world.getHeight()).getRandomInt();

            Region region = world.getRegion(x, y);
            BoundingBox box = region.getWorldBounds();
            Coordinates regionStart = new Coordinates(box.startingPoint().x(), box.startingPoint().y());
            Coordinates regionEnd = new Coordinates(box.endPoint().x(), box.endPoint().y());

            if (Objects.equals(config.getType(), "rift")) {
                startingTile = lowestPoint(region, regionStart, regionEnd);
            } else {
                startingTile = highestPoint(region, regionStart, regionEnd);
            }

            Coordinates coordsStart = new Coordinates(startingTile.getWorldX(), startingTile.getWorldY());

            Coordinates coordsEnd;
            int width = world.getWidthInTiles();
            int height = world.getHeightInTiles();
            while (true) {
                coordsEnd = new RandomSpot(width, height).getCoords();
                float distance = new Distance(coordsStart.x(), coordsStart.y(), coordsEnd.x(), coordsEnd.y()).euclidianDistance();
                if (distance >= config.getDistanceMin() && distance < config.getDistanceMax()) {
                    break;
                }
            }

            int radius = new RandomInteger(config.getMinRadius(), config.getMaxRadius()).getRandomInt();
            float influence = (float) (config.getStrength() * Math.random());

            Line line = new Line(coordsStart, coordsEnd);
            EffectTarget target = new AddEffectTarget();

            NoiseService noise = world.getNoise();
            Shape divideMap = new DivideMapShape(line, config.getType(), radius, noise, influence);

            ShapeEffect tectonic = new ShapeEffect(divideMap, target);

            world.applyShapeEffect(tectonic);
        }
        //consoleFeedback("Tectonic " + type + " x " + count);
    }


    private TileWithCoordinates lowestPoint(Region region, Coordinates start, Coordinates end) {

        Tile minTile = null;
        int minTileX = 0;
        int minTileY = 0;
        float altitudeMin = 1.1f;

        for (int i = start.y(); i < end.y(); i++) {
            for (int j = start.x(); j < end.x(); j++) {
                Tile currentTile = region.getTile(j - start.x(), i - start.y());
                float alt = currentTile.getAltitude();

                if (alt < altitudeMin) {
                    altitudeMin = alt;
                    minTileX = j;
                    minTileY = i;
                    minTile = currentTile;
                }
            }
        }
        return new TileWithCoordinates(minTile, minTileX, minTileY);
    }

    private TileWithCoordinates highestPoint(Region region, Coordinates start, Coordinates end) {

        Tile maxTile = null;
        int maxTileX = 0;
        int maxTileY = 0;
        float altitudeMax = -1.1f;

        for (int i = start.y(); i < end.y(); i++) {
            for (int j = start.x(); j < end.x(); j++) {
                Tile currentTile = region.getTile(j - start.x(), i - start.y());
                float alt = currentTile.getAltitude();

                if (alt > altitudeMax) {
                    altitudeMax = alt;
                    maxTileX = j;
                    maxTileY = i;
                    maxTile = currentTile;
                }
            }
        }
        return new TileWithCoordinates(maxTile, maxTileX, maxTileY);
    }


    @Override
    public String getName() {
        return "Tectonic";
    }

    @Override
    public List<Parameter<?>> createParameters(TectonicConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations", 1, 250, config.getCount(), config::setCount));
        parameters.add(new IntParameter("Minimum influence radius", 1, 100, config.getMinRadius(), config::setMinRadius));
        parameters.add(new IntParameter("Maximum influence radius", 1, 100, config.getMaxRadius(), config::setMaxRadius));
        parameters.add(new IntParameter("Minimum " + config.getType() + " length", 100, 1000, config.getDistanceMin(), config::setDistanceMin));
        parameters.add(new IntParameter("Maximum " + config.getType() + " length", 100, 1000, config.getDistanceMax(), config::setDistanceMax));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, config.getStrength(), 0.01f, config::setStrength));

        return parameters;
    }

}
