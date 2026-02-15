package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.WorldIterations;
import com.yorkhuul.life.core.world.WorldMutations;
import com.yorkhuul.life.core.world.WorldQueries;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.utils.position.ArraytoMatrixIndex;

public class WaterErosion implements HydrologyStep<ErosionConfig> {

    @Override
    public void apply(World world, ErosionConfig config) {
        int width = WorldQueries.getWorldWidth();

        WorldIterations.forEachTile(world, (x, y, tile) -> {
            HydrologyContext context = world.getHydrologyContext();
            int index = ArraytoMatrixIndex.getIndex(x, y, width);
            float[] sediment = context.sediment;

            if (tile.getAltitude() <= world.getSeaLevel()) {
                depositAllSediment(tile, sediment, index);
                return;
            }

            float flow = context.flow[index];
            if (flow <= 0) return;


            applyErosion(tile, flow, sediment, index, config);
        });
    }

    private void applyErosion(Tile tile, float flow, float[] sediment, int index, ErosionConfig config) {
        float capacity = flow * config.getStrength() * config.getSedimentCapacityCoefficient();

        if (sediment[index] > capacity) {
            float deposit = sediment[index] - capacity;
            sediment[index] -= deposit;
            WorldMutations.addAltitude(tile, deposit);
        } else {
            float erosion = Math.min(
                    capacity - sediment[index],
                    config.getMaxErosionPerStep()
            );

            WorldMutations.addAltitude(tile, -erosion);
            sediment[index] += erosion;
        }
    }

    private void depositAllSediment(Tile tile, float[] sediment, int index) {
        WorldMutations.addAltitude(tile, sediment[index]);
        sediment[index] = 0;
    }

    @Override
    public String getName() {
        return "Erosion";
    }

}