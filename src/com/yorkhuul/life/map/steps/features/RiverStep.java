package com.yorkhuul.life.map.steps.features;

import com.yorkhuul.life.map.zone.RiverData;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

public class RiverStep implements FeatureStep{

    private final float RIVER_THRESHOLD = 0.02f;
    private final float MAX_WIDTH = 1f;


    @Override
    public void apply(World world) {
        float maxCumulativeFlow = world.getMaxCumulativeFlow();
        System.out.println("max flow: " + maxCumulativeFlow);

        for (Tile tile: world.getAllTiles()) {
            RiverData river = tile.getRiver();
            float normalizedFlow = river.computeNormalizedFlow(maxCumulativeFlow);
            river.setWidth(getRiverWidth(normalizedFlow));
            //if (normalizedFlow > 0) System.out.println(river);
        }
    }

    private float getRiverWidth(float normalizedFlow) {
        if (normalizedFlow > RIVER_THRESHOLD) return (float) (Math.sqrt(normalizedFlow) * MAX_WIDTH);
        else return 0f;
    }
}
