package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;


public class BorderConfig implements StepConfig {
    private int coastWidth;
    private float strength;

    public int getCoastWidth() {
        return coastWidth;
    }

    public float getStrength() {
        return strength;
    }

    public void setCoastWidth(int coastWidth) {
        this.coastWidth = coastWidth;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Water border width", 1, 500, getCoastWidth(), this::setCoastWidth));
        parameters.add(new FloatParameter("Effect strength",0.01f, 1f, getStrength(), 0.01f, this::setStrength));

        return parameters;
    }
}
