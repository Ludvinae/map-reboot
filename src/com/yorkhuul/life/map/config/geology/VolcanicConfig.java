package com.yorkhuul.life.map.config.geology;

import com.yorkhuul.life.map.config.StepConfig;

public class VolcanicConfig implements StepConfig {
    private int count;
    private int minRadius;
    private int maxRadius;
    private float strength;

    public int getCount() {
        return count;
    }

    public int getMinRadius() {
        return minRadius;
    }

    public int getMaxRadius() {
        return maxRadius;
    }

    public float getStrength() {
        return strength;
    }

    public void setCount(int count) {
        if (count < 1) count = 1;
        this.count = count;
    }

    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
    }

    public void setMaxRadius(int maxRadius) {
        if (maxRadius < minRadius) maxRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
