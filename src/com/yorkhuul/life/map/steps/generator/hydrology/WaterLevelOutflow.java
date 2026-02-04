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
        HydrologyContext context = world.getHydrologyContext();

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = context.getIndex(worldX, worldY);

            float water = context.water[index];
            if (water <= 0) return;
            float surface = tile.getAltitude() + water;

            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {
                    if (j == 0 && i == 0) continue;

                    int x = worldX + j;
                    int y = worldY + i;
                    if (!world.isInBounds(x, y)) continue;

                    int indexNeighbor = context.getIndex(x, y);
                    Tile neighbor = world.getTileWithWorldCoordinates(x, y);

                    float neighborSurface = neighbor.getAltitude() + context.water[indexNeighbor];

                    float delta = surface - neighborSurface;
                    if (delta <= minDelta) continue;

                    float transfer = delta * flowStrength;
                    transfer = Math.min(transfer, water);

                    context.waterBuffer[index] -= transfer;
                    context.waterBuffer[indexNeighbor] += transfer;
                }
            }
        });
        // Application of the buffer
        context.applyWaterBuffer();
    }



}
