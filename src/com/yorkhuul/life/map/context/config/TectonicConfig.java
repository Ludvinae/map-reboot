package com.yorkhuul.life.map.context.config;

public class TectonicConfig implements StepConfig{

    private int count;
    private String type;
    private float frequency;
    private int minRadius;
    private int maxRadius;
    private float distanceMin;
    private float distanceMax;
    private float strength;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
        this.minRadius = minRadius;
    }

    public int getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
        this.maxRadius = maxRadius;
    }

    public float getDistanceMin() {
        return distanceMin;
    }

    public void setDistanceMin(float distanceMin) {
        this.distanceMin = distanceMin;
    }

    public float getDistanceMax() {
        return distanceMax;
    }

    public void setDistanceMax(float distanceMax) {
        this.distanceMax = distanceMax;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
