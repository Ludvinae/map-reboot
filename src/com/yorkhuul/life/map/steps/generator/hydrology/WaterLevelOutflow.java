package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

public class WaterLevelOutflow implements HydrologyStep{

    private int iterations;
    private float outflowStrength;
    private float minDelta; // permet d'eviter les recalculs constant de transfert entre des tiles avec une surface proche
    private final float SQRT2 = 1.4142f;

    public WaterLevelOutflow(int iterations, float outflowStrength, float minDelta) {
        this.iterations = iterations;
        this.outflowStrength = outflowStrength;
        this.minDelta = minDelta;
    }

    public WaterLevelOutflow(float outflowStrength, float minDelta) {
        this(5, outflowStrength, minDelta);
    }

    public WaterLevelOutflow(float outflowStrength) {
        this(5, outflowStrength, 0.005f);
    }


    @Override
    public void apply(World world) {


        for (int i = 0; i < iterations; i++) {
            outflow(world);
        }
    }

    private void outflow(World world){
        HydrologyContext context = world.getHydrologyContext();

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = context.getIndex(worldX, worldY);

            float water = context.water[index];
            if (water <= 0) return;
            float surface = tile.getAltitude() + water;

            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {
                    if (j == 0 && i == 0) continue;
                    if (water <= 0) continue;

                    int x = worldX + j;
                    int y = worldY + i;
                    if (!world.isInBounds(x, y)) continue;

                    int indexNeighbor = context.getIndex(x, y);
                    Tile neighbor = world.getTileWithWorldCoordinates(x, y);

                    float neighborSurface = neighbor.getAltitude() + context.water[indexNeighbor];
                    float distancePonderation = 1f;
                    if (i != 0 && j != 0) distancePonderation = SQRT2;

                    float delta = (surface - neighborSurface) / distancePonderation;
                    if (delta <= minDelta) continue;

                    float transfer = delta * outflowStrength;
                    transfer = Math.min(transfer, water);

                    water -= transfer;
                    surface -= transfer;
                    context.waterBuffer[index] -= transfer;
                    context.waterBuffer[indexNeighbor] += transfer;
                }
            }
        });
        // Application of the buffer
        context.applyWaterBuffer();
    }



}
