package com.yorkhuul.life.core.engine.pipeline.features;

import com.yorkhuul.life.core.engine.pipeline.hydrology.HydrologyContext;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.WorldIterations;
import com.yorkhuul.life.core.world.WorldQueries;
import com.yorkhuul.life.utils.position.ArraytoMatrixIndex;

public class RiverStep implements FeatureStep<RiverConfig>{

    @Override
    public void apply(World world, RiverConfig config) {
        HydrologyContext context = world.getHydrologyContext();
        float maxCumulativeFlow = context.getMaxCumulativeFlow();
        System.out.println("max cumulative flow: " + maxCumulativeFlow);
        if (maxCumulativeFlow == 0) return;

        int width = WorldQueries.getWorldWidth();

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = ArraytoMatrixIndex.getIndex(worldX, worldY, width);
            float normalizedFlow = context.cumulativeFlow[index] / maxCumulativeFlow;
            context.riverWidth[index] = getRiverWidth(normalizedFlow, config);
        });
        WorldQueries.setMaxCumulativeFlow(maxCumulativeFlow);
    }

    private float getRiverWidth(float normalizedFlow, RiverConfig config) {
        float riverThreshold = config.getRIVER_THRESHOLD();
        if (normalizedFlow < riverThreshold) return 0f;

        float t = (normalizedFlow - riverThreshold) / (1f - riverThreshold);
        return (float) (Math.sqrt(t) * config.getMAX_WIDTH());
    }
}
