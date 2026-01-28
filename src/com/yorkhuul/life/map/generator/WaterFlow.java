package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.tools.TileWithCoordinates;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

public class WaterFlow implements GenerationStep{

    private int count;

    public WaterFlow(int count) {
        this.count = count;
    }

    @Override
    public void apply(World world) {
        world.computeLowestTileNeighbor();
        float[][] buffer = new float[world.getHeightInTiles()][world.getWidthInTiles()];

        for (int i = 0; i < count; i++) {
            flow(world, buffer);
            applyBuffer(world, buffer);
        }
    }

    private void flow(World world, float[][] buffer) {
        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            TileWithCoordinates neighbor = tile.getFlowTarget();

            if (neighbor != null) {
                float waterFlow = tile.getWater();
                buffer[worldY][worldX] = -waterFlow;
                buffer[neighbor.worldY()][neighbor.worldX()] = waterFlow;

            }
        });
    }

    private void applyBuffer(World world, float[][] buffer) {

    }


}
