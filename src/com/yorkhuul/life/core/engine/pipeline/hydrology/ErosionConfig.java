package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class ErosionConfig implements StepConfig {
    private float sedimentCapacityCoefficient;
    private float maxErosionPerStep;
    private float strength;

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getMaxErosionPerStep() {
        return maxErosionPerStep;
    }

    public void setMaxErosionPerStep(float maxErosionPerStep) {
        this.maxErosionPerStep = maxErosionPerStep;
    }

    public float getSedimentCapacityCoefficient() {
        return sedimentCapacityCoefficient;
    }

    public void setSedimentCapacityCoefficient(float sedimentCapacityCoefficient) {
        this.sedimentCapacityCoefficient = sedimentCapacityCoefficient;
    }

    @Override
    public List<Parameter<?>> createParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new FloatParameter("Tiles sediment capacity", 0.01f, 1f, getSedimentCapacityCoefficient(), 0.01f, this::setSedimentCapacityCoefficient));
        parameters.add(new FloatParameter("Maximum erosion effect", 0.001f, 0.1f, getMaxErosionPerStep(), 0.001f, this::setMaxErosionPerStep));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, getStrength(), 0.01f, this::setStrength));

        return parameters;
    }
}
