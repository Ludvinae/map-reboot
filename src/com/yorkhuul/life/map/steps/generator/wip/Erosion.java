package com.yorkhuul.life.map.steps.generator.wip;


import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class Erosion {

    private int iterations;
    private float seaLevel;
    private float minHeightDelta;
    private float strength;
    List<Parameter<?>> parameters = new ArrayList<>();

    public Erosion(int iterations, float seaLevel, float minHeightDelta, float strength) {
        setIterations(iterations);
        this.seaLevel = seaLevel;
        this.minHeightDelta = minHeightDelta;
        this.strength = strength;
    }

    public Erosion(float strength) {
        this(1, 0, 0.01f, strength);
    }

    public void setIterations(int iterations) {
        if (iterations < 1) iterations = 1;
        this.iterations = iterations;
    }


    public void apply(World world, EditorContext context) {
        float[][] buffer = new float[world.getHeightInTiles()][world.getWidthInTiles()];

        for (int i = 0; i < iterations; i++) {
            erode(world, buffer);
            world.forEachTile((region, localX, localY, worldX, worldY) -> {
                Tile tile = region.getTile(localX, localY);
                tile.addAltitude(buffer[worldY][worldX]);
            });
        }

    }

    private void erode(World world, float[][] buffer) {

        world.forEachTileWithNeighbors(((region, localX, localY, worldX, worldY, tile, neighbors) -> {
            if (tile.getAltitude() <= seaLevel) return;

            for (TileWithCoordinates neighbor : neighbors) {
                float delta = tile.getAltitude() - neighbor.getAltitude();

                if (delta > minHeightDelta) {
                    float amount = (delta - minHeightDelta) * strength;
                    buffer[worldY][worldX] -= amount;
                    buffer[neighbor.getWorldY()][neighbor.getWorldX()] += amount;
                }
            }

        }));
    }

}
