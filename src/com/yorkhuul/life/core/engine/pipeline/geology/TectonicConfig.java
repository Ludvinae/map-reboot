package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.Objects;

public class TectonicConfig implements StepConfig {

    private int count;
    private String type;
    // frequency will be used when refactoring Tectonic to use noise instead of relying on Math.random
    private float frequency;
    private int minRadius;
    private int maxRadius;
    private int distanceMin;
    private int distanceMax;
    private float strength;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0) count = 1;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!Objects.equals(type, "rift") && !Objects.equals(type, "subduction")) {
            type = "subduction";
        }
        this.type = type;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public int getMinRadius() {
        return minRadius;
    }

    public void setMinRadius(int minRadius) {
        if (minRadius <= 0) minRadius = 1;
        if (minRadius > maxRadius) minRadius = maxRadius;
        this.minRadius = minRadius;
    }

    public int getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
        if (maxRadius < minRadius) maxRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    public int getDistanceMin() {
        return distanceMin;
    }

    public void setDistanceMin(int distanceMin) {
        if (distanceMin <= 0) distanceMin = 1;
        if (distanceMin > distanceMax) distanceMin = distanceMax;
        this.distanceMin = distanceMin;
    }

    public int getDistanceMax() {
        return distanceMax;
    }

    public void setDistanceMax(int distanceMax) {
        if (distanceMax < distanceMin) distanceMax = distanceMin;
        this.distanceMax = distanceMax;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
