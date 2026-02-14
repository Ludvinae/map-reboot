package com.yorkhuul.life.core.engine.config.geology;

import com.yorkhuul.life.core.engine.config.StepConfig;

public class NoiseConfig implements StepConfig {
    private float frequency;
    private int OFFSET = 57;
    private float strength;

    public float getFrequency() {
        return frequency;
    }

    public int getOffset() {
        return OFFSET;
    }

    public float getStrength() {
        return strength;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
