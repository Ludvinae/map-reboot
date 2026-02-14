package com.yorkhuul.life.core.engine.config.hydrology;

import com.yorkhuul.life.core.engine.config.StepConfig;

public class FlowConfig implements StepConfig {
    private float strength;

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
