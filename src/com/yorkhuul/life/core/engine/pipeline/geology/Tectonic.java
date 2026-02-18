package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.engine.shape.DivideMapShape;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddEffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.Line;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.utils.position.*;
import com.yorkhuul.life.core.world.region.Region;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.tile.TileWithCoordinates;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.utils.random.RandomInteger;
import com.yorkhuul.life.utils.random.RandomSpot;


public class Tectonic implements GenerationStep<TectonicConfig> {

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

            if (config.getType() == TectonicType.RIFT) {
                startingTile = lowestPoint(region, regionStart, regionEnd);
            } else if (config.getType() == TectonicType.SUBDUCTION){
                startingTile = highestPoint(region, regionStart, regionEnd);
            } else throw new IllegalArgumentException("Tectonic type not supported");

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
    public boolean isOptional() {
        return true;
    }

}
