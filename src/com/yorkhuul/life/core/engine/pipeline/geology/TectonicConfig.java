package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TectonicConfig implements StepConfig {

    private int count = 100;
    private String type = "subduction";
    // frequency will be used when refactoring Tectonic to use noise instead of relying on Math.random
    private float frequency = 0.0002f;
    private int minRadius = 10;
    private int maxRadius = 50;
    private int distanceMin = 110;
    private int distanceMax = 250;
    private float strength = 0.5f;

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

    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations", 1, 250, getCount(), this::setCount));
        // need to add frequency to the sliders once it's used by the class
        parameters.add(new IntParameter("Minimum influence radius", 1, 100, getMinRadius(), this::setMinRadius));
        parameters.add(new IntParameter("Maximum influence radius", 1, 100, getMaxRadius(), this::setMaxRadius));
        parameters.add(new IntParameter("Minimum " + getType() + " length", 100, 1000, getDistanceMin(), this::setDistanceMin));
        parameters.add(new IntParameter("Maximum " + getType() + " length", 100, 1000, getDistanceMax(), this::setDistanceMax));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, getStrength(), 0.01f, this::setStrength));

        return parameters;
    }
}
