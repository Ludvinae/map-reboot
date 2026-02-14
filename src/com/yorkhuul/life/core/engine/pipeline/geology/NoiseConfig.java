package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;

public class NoiseConfig implements StepConfig {
    private float frequency;
    private int OFFSET = 57;
    private float strength;

    // Temporarily divide frequency by 10000
    public float getFrequency() {
        return frequency / 10000;
    }

    public int getOffset() {
        return OFFSET;
    }

    // Temporarily divide strength by 100, should be removed once parameters are implemented fully
    public float getStrength() {
        return strength / 100;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Frequency : "  + frequency + ", Strength : " + strength;
    }
}
