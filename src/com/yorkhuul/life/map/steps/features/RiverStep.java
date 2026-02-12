package com.yorkhuul.life.map.steps.features;

import com.yorkhuul.life.map.context.config.features.RiverConfig;
import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;

public class RiverStep implements FeatureStep<RiverConfig>{

    @Override
    public void apply(World world, RiverConfig config) {
        HydrologyContext context = world.getHydrologyContext();
        float maxCumulativeFlow = context.getMaxCumulativeFlow();
        System.out.println("max cumulative flow: " + maxCumulativeFlow);
        if (maxCumulativeFlow == 0) return;

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = context.getIndex(worldX, worldY);
            float normalizedFlow = context.cumulativeFlow[index] / maxCumulativeFlow;
            context.riverWidth[index] = getRiverWidth(normalizedFlow, config);
        });
    }

    private float getRiverWidth(float normalizedFlow, RiverConfig config) {
        float riverThreshold = config.getRIVER_THRESHOLD();
        if (normalizedFlow < riverThreshold) return 0f;

        float t = (normalizedFlow - riverThreshold) / (1f - riverThreshold);
        return (float) (Math.sqrt(t) * config.getMAX_WIDTH());
    }
}
