package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.world.WorldQueries;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.WorldIterations;
import com.yorkhuul.life.utils.position.ArraytoMatrixIndex;

public class WaterLevelOutflow implements GenerationStep<OutflowConfig> {

    @Override
    public void apply(World world, OutflowConfig config) {

        for (int i = 0; i < config.getIterations(); i++) {
            outflow(world, config);
        }
    }

    private void outflow(World world, OutflowConfig config) {
        HydrologyContext context = world.getHydrologyContext();
        int width = WorldQueries.getWorldWidth();

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = ArraytoMatrixIndex.getIndex(worldX, worldY, width);

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

                    int indexNeighbor = ArraytoMatrixIndex.getIndex(x, y, width);
                    Tile neighbor = world.getTileWithWorldCoordinates(x, y);

                    float neighborSurface = neighbor.getAltitude() + context.water[indexNeighbor];
                    float distancePonderation = 1f;
                    if (i != 0 && j != 0) distancePonderation = config.getSQRT2();

                    float delta = (surface - neighborSurface) / distancePonderation;
                    if (delta <= config.getSQRT2()) continue;

                    float transfer = delta * config.getOutflowStrength();
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

    @Override
    public String getName() {
        return "Outflow";
    }

}
