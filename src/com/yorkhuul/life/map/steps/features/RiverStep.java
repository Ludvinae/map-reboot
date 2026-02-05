package com.yorkhuul.life.map.steps.features;

import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;

public class RiverStep implements FeatureStep{

    private final float RIVER_THRESHOLD = 0.02f;
    private final float MAX_WIDTH = 1f;


    @Override
    public void apply(World world) {
        HydrologyContext context = world.getHydrologyContext();
        float maxCumulativeFlow = context.getMaxCumulativeFlow();
        System.out.println("max cumulative flow: " + maxCumulativeFlow);
        if (maxCumulativeFlow == 0) return;

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = context.getIndex(worldX, worldY);
            float normalizedFlow = context.cumulativeFlow[index] / maxCumulativeFlow;
            context.riverWidth[index] = getRiverWidth(normalizedFlow);
        });
    }

    private float getRiverWidth(float normalizedFlow) {
        if (normalizedFlow < RIVER_THRESHOLD) return 0f;

        float t = (normalizedFlow - RIVER_THRESHOLD) / (1f - RIVER_THRESHOLD);
        return (float) (Math.sqrt(t) * MAX_WIDTH);
    }
}
