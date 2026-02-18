package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class RainConfig implements StepConfig {
    private int count = 100;
    private int minRadius = 10;
    private int maxRadius = 50;
    private float rainfallAmount = 0.75f;

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


    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations count", 1, 500, getCount(), this::setCount));
        parameters.add(new IntParameter("Minimum influence radius", 1, 100, getMinRadius(), this::setMinRadius));
        parameters.add(new IntParameter("Maximum influence radius", 1, 100, getMaxRadius(), this::setMaxRadius));
        parameters.add(new FloatParameter("Rainfall amount", 0.01f, 1f, getRainfallAmount(), 0.01f, this::setRainfallAmount));

        return parameters;
    }
}
