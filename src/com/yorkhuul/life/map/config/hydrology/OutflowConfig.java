package com.yorkhuul.life.map.config.hydrology;

import com.yorkhuul.life.map.config.StepConfig;

public class OutflowConfig implements StepConfig {
    private int iterations;
    private float outflowStrength;
    private float minDelta; // permet d'eviter les recalculs constant de transfert entre des tiles avec une surface proche
    private final float SQRT2 = 1.4142f;

    public int getIterations() {
        return iterations;
    }

    public float getOutflowStrength() {
        return outflowStrength;
    }

    public float getMinDelta() {
        return minDelta;
    }

    public float getSQRT2() {
        return SQRT2;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setOutflowStrength(float outflowStrength) {
        this.outflowStrength = outflowStrength;
    }

    public void setMinDelta(float minDelta) {
        this.minDelta = minDelta;
    }
}
