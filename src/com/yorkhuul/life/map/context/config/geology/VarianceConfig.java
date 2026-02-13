package com.yorkhuul.life.map.context.config.geology;

import com.yorkhuul.life.map.context.config.StepConfig;

public class VarianceConfig implements StepConfig {
    private float noiseFrequency;
    private float amplitude;

    public float getNoiseFrequency() {
        return noiseFrequency;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setNoiseFrequency(float noiseFrequency) {
        this.noiseFrequency = noiseFrequency;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }
}
