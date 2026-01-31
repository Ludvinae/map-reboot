package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.TileWithCoordinates;
import com.yorkhuul.life.map.zone.World;

public class WaterLevelOutflow implements HydrologyStep{

    private float flowStrength;
    private float minDelta; // permet d'eviter les recalculs constant de transfert entre des tiles avec une surface proche

    public WaterLevelOutflow(float flowStrength, float minDelta) {
        this.flowStrength = flowStrength;
        this.minDelta = minDelta;
    }

    public WaterLevelOutflow(float flowStrength) {
        this(flowStrength, 0.01f);
    }

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
        consoleFeedback("Water outflow ");
    }
}
