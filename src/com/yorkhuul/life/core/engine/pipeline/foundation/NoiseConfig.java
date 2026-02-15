package com.yorkhuul.life.core.engine.pipeline.foundation;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.LogParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class NoiseConfig implements StepConfig {
    private float frequency;
    private int OFFSET = 57;
    private float strength;

    // Temporarily divide frequency by 10000
    public float getFrequency() {
        return frequency / 10000;
    }

    public int getOffset() {
        return OFFSET;
    }

    // Temporarily divide strength by 100, should be removed once parameters are implemented fully
    public float getStrength() {
        return strength / 100;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Frequency : "  + frequency + ", Strength : " + strength;
    }


    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new LogParameter("Pattern repetition frequency", 0.00001f, 0.1f, getFrequency(), 500, this::setFrequency));
        parameters.add(new FloatParameter("Altitude amplitude", 0.01f, 1f, getStrength(), 0.01f, this::setStrength));

        return parameters;
    }
}
