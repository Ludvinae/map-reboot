package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class OutflowConfig implements StepConfig {
    private int iterations;
    private float outflowStrength;
    private float minDelta; // permet d'eviter les recalculs constant de transfert entre des tiles avec une surface proche
    private final float SQRT2 = 1.4142f;

    public int getIterations() {
        return iterations;
    }

    public float getOutflowStrength() {
        return outflowStrength;
    }

    public float getMinDelta() {
        return minDelta;
    }

    public float getSQRT2() {
        return SQRT2;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setOutflowStrength(float outflowStrength) {
        this.outflowStrength = outflowStrength;
    }

    public void setMinDelta(float minDelta) {
        this.minDelta = minDelta;
    }

    @Override
    public List<Parameter<?>> createParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations count", 1, 500, getIterations(), this::setIterations));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, getOutflowStrength(), 0.01f, this::setOutflowStrength));
        parameters.add(new FloatParameter("Minimum altitude difference", 0.001f, 0.1f, getMinDelta(), 0.001f, this::setMinDelta));

        return parameters;
    }
}
