package com.yorkhuul.life.map.config;

import static java.lang.Math.clamp;

public class WorldConfig {

    private String name;
    private String seed;
    private int width;
    private int height;
    private float frequency;
    private float amplitude;

    public WorldConfig() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = clamp(width, 8, 64);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = clamp(height, 8, 64);
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = clamp(frequency, 0.0001f, 0.1f);
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = clamp(amplitude, 0.1f, 1f);
    }
}
