package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;

public class FlowConfig implements StepConfig {
    private float strength;

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
