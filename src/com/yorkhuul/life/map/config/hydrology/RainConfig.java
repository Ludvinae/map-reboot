package com.yorkhuul.life.map.config.hydrology;

import com.yorkhuul.life.map.config.StepConfig;

public class RainConfig implements StepConfig {
    private int count;
    private int minRadius;
    private int maxRadius;
    private float rainfallAmount;

    public float getRainfallAmount() {
        return rainfallAmount;
    }

    public void setRainfallAmount(float rainfallAmount) {
        this.rainfallAmount = rainfallAmount;
    }

    public int getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
        this.maxRadius = maxRadius;
    }

    public int getMinRadius() {
        return minRadius;
    }

    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



}
