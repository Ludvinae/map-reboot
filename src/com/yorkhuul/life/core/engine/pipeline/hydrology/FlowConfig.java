package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class FlowConfig implements StepConfig {
    private float strength;

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public List<Parameter<?>> createParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, getStrength(), 0.01f, this::setStrength));

        return parameters;
    }
}
