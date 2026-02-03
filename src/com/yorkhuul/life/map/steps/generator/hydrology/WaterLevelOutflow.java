package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

public class WaterLevelOutflow implements HydrologyStep{

    private float flowStrength;
    private float minDelta; // permet d'eviter les recalculs constant de transfert entre des tiles avec une surface proche

    public WaterLevelOutflow(float flowStrength, float minDelta) {
        this.flowStrength = flowStrength;
        this.minDelta = minDelta;
    }

    public WaterLevelOutflow(float flowStrength) {
        this(flowStrength, 0.002f);
    }


    @Override
    public void apply(World world) {
        float[][] buffer = new float[world.getHeightInTiles()][world.getWidthInTiles()];

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {
                    if (j == 0 && i == 0) return;

                    int x = worldX + j;
                    int y = worldY + i;
                    if (!world.isInBounds(x, y)) return;

                    float water = tile.getWater();
                    if (water <= 0) return;

                    Tile neighbor = world.getTileWithWorldCoordinates(x, y);
                    float surface = tile.waterSurface();
                    float neighborSurface = neighbor.waterSurface();

                    float delta = surface - neighborSurface;
                    if (delta <= minDelta) return;

                    float transfer = delta * flowStrength;
                    transfer = Math.min(transfer, water);

                    buffer[y][x] += transfer;
                    buffer[worldY][worldX] -= transfer;
                }
            }
        });
        // Application of the buffer
        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            WorldMutations.addWater(tile, buffer[worldY][worldX]);
        });
    }


    /*
    @Override
    public void apply(World world) {
        float[][] buffer = new float[world.getHeightInTiles()][world.getWidthInTiles()];

        world.forEachTileWithNeighbors(
            (region, localX, localY, worldX, worldY, tile, neighbors) -> {

                float altitude = tile.getAltitude();

                // Mer = puits
                if (altitude <= world.getSeaLevel()) {
                    tile.setWater(0);
                    return;
                }

                float water = tile.getWater();
                if (water <= 0) return;

                float surface = tile.waterSurface();
                float totalOut = 0;

                for (TileWithCoordinates neighbor : neighbors) {

                    float nSurface = neighbor.getTile().waterSurface();
                    float delta = surface - nSurface;

                    if (delta <= minDelta) continue;

                    float transfer = delta * flowStrength;
                    transfer = Math.min(transfer, water - totalOut);
                    //System.out.println(transfer);
                    if (transfer <= 0) continue;

                    buffer[worldY][worldX] -= transfer;
                    buffer[neighbor.getWorldY()][neighbor.getWorldX()] += transfer;

                    totalOut += transfer;
                    if (totalOut >= water) break;
                }
            }
        );

        // Application du buffer
        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            tile.addWater(buffer[worldY][worldX]);
        });
        //consoleFeedback("Water outflow ");
    }

     */
}
