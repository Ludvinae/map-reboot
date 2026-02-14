package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;


public class BorderConfig implements StepConfig {
    private int coastWidth;
    private float strength;

    public int getCoastWidth() {
        return coastWidth;
    }

    public float getStrength() {
        return strength;
    }

    public void setCoastWidth(int coastWidth) {
        this.coastWidth = coastWidth;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
