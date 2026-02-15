package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Parameter<?>> createParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations count", 1, 500, getCount(), this::setCount));
        parameters.add(new IntParameter("Minimum influence radius", 1, 100, getMinRadius(), this::setMinRadius));
        parameters.add(new IntParameter("Maximum influence radius", 1, 100, getMaxRadius(), this::setMaxRadius));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, getStrength(), 0.01f, this::setStrength));

        return parameters;
    }
}
