package com.yorkhuul.life.map.context.config.features;

import com.yorkhuul.life.map.context.config.StepConfig;

public class RiverConfig implements StepConfig {

    private final float RIVER_THRESHOLD = 0.005f;
    private final float MAX_WIDTH = 1f;

    public float getRIVER_THRESHOLD() {
        return RIVER_THRESHOLD;
    }

    public float getMAX_WIDTH() {
        return MAX_WIDTH;
    }
}
