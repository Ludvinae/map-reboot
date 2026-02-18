package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.LogParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class VarianceConfig implements StepConfig {
    private float noiseFrequency = 0.001f;
    private float amplitude = 0.4f;

    public float getNoiseFrequency() {
        return noiseFrequency;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setNoiseFrequency(float noiseFrequency) {
        this.noiseFrequency = noiseFrequency;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new LogParameter("Variance noise frequency", 0.0001f, 0.1f, getNoiseFrequency(), 250, this::setNoiseFrequency));
        parameters.add(new FloatParameter("Altitude difference amplitude", 0.01f, 1f, getAmplitude(), 0.01f, this::setAmplitude));

        return parameters;
    }
}
