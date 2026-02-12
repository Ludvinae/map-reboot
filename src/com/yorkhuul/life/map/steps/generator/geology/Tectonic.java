package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.parameters.FloatParameter;
import com.yorkhuul.life.map.parameters.IntParameter;
import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.config.TectonicConfig;
import com.yorkhuul.life.map.effect.*;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.shape.DivideMapShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.*;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;

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
            while (true) {
                coordsEnd = new RandomSpot(world).getCoords();
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
