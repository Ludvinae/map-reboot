package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class FloatParameter extends SliderParameter<Float> {

    private final float scale;

    public FloatParameter(String name,
                           int min,
                           int max,
                           float initialValue,
                           float scale,
                           Consumer<Float> onChange) {
        super(name, min, max, initialValue, onChange);
        this.scale = scale;
    }

    @Override
    public Float fromSlider(int sliderValue) {
        return sliderValue / scale;
    }

    @Override
    public int toSlider(Float value) {
        return (int) (value * scale);
    }

    @Override
    public String format(Float value) {
        return String.format("%.2f", value);
    }
}


