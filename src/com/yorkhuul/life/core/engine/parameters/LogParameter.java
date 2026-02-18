package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class LogParameter extends SliderParameter<Float> {

    private final float minValue;
    private final float maxValue;
    private final int sliderResolution;

    public LogParameter(String name,
                             float minValue,
                             float maxValue,
                             float initialValue,
                             int sliderResolution,
                             Consumer<Float> onChange) {
        super(name, 0, sliderResolution, initialValue, onChange);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.sliderResolution = sliderResolution;
    }

    @Override
    public Float fromSlider(int sliderValue) {

        float t = sliderValue / (float) sliderResolution;

        return (float) (minValue *
                Math.pow(maxValue / minValue, t));
    }

    @Override
    public int toSlider(Float value) {

        float t = (float) (Math.log(value / minValue) /
                Math.log(maxValue / minValue));

        return (int) (t * sliderResolution);
    }

    @Override
    public String format(Float value) {
        return String.format("%.4f", value);
    }
}


