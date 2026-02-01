package com.yorkhuul.life.map.generator.geology;

import com.yorkhuul.life.map.effect.AddEffect;
import com.yorkhuul.life.map.effect.Effect;
import com.yorkhuul.life.map.effect.Line;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.shape.DivideMapShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.*;
import com.yorkhuul.life.map.zone.*;

import java.util.Objects;

public class Tectonic implements GenerationStep {

    private int count;
    private String type;
    private float frequency;
    private int influenceMin;
    private int influenceMax;
    private int distanceMin;
    private int distanceMax;
    private float strength;

    public Tectonic(int count, String type, float frequency, int minRadius, int maxRadius, int distanceMin, int distanceMax, float strength) {
        setCount(count);
        setType(type);
        this.influenceMin = minRadius;
        this.influenceMax = maxRadius;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
        this.strength = strength;
    }

    public Tectonic(int count, String type, int minRadius, int maxRadius, int distanceMin, int distanceMax, float strength) {
        this(count, type, 0.01f, minRadius, maxRadius, distanceMin, distanceMax, strength);
    }


    // Setters
    public void setCount(int count) {
        if (count <= 1) {
            count = 1;
        }
        this.count = count;
    }

    public void setType(String type) {
        if (!Objects.equals(type, "rift") && !Objects.equals(type, "subduction")) {
            type = "subduction";
        }
        this.type = type;
    }

    public void setInfluenceMax(int influenceMax) {
        if (influenceMax <= this.influenceMin) {
            influenceMax = this.influenceMin + 1;
        }
        this.influenceMax = influenceMax;
    }

    //Methods
    @Override
    public void apply(World world) {
        TileWithCoordinates startingTile;
        for (int i = 0; i < this.count; i++) {
            int x = new RandomInteger(0, world.getWidth()).getRandomInt();
            int y = new RandomInteger(0, world.getHeight()).getRandomInt();

            Region region = world.getRegion(x, y);
            BoundingBox box = region.getWorldBounds();
            Coordinates regionStart = new Coordinates(box.startingPoint().x(), box.startingPoint().y());
            Coordinates regionEnd = new Coordinates(box.endPoint().x(), box.endPoint().y());

            if (Objects.equals(type, "rift")) {
                startingTile = lowestPoint(region, regionStart, regionEnd);
            } else {
                startingTile = highestPoint(region, regionStart, regionEnd);
            }

            Coordinates coordsStart = new Coordinates(startingTile.getWorldX(), startingTile.getWorldY());

            Coordinates coordsEnd;
            while (true) {
                coordsEnd = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();
                float distance = new Distance(coordsStart.x(), coordsStart.y(), coordsEnd.x(), coordsEnd.y()).euclidianDistance();
                if (distance >= distanceMin && distance < distanceMax) {
                    break;
                }
            }

            int radius = new RandomInteger(this.influenceMin, this.influenceMax).getRandomInt();
            float influence = (float) (strength * Math.random());

            Line line = new Line(coordsStart, coordsEnd);
            Effect effect = new AddEffect();

            NoiseService noise = world.getNoise();
            Shape divideMap = new DivideMapShape(line, type, radius, noise, influence);

            ShapeEffect tectonic = new ShapeEffect(divideMap, effect);

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


}
