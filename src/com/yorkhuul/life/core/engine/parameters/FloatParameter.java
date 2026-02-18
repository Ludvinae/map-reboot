package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class FloatParameter extends SliderParameter<Float> {

    private final float scale;

    public FloatParameter(String name,
                           float min,
                           float max,
                           float initialValue,
                           float step,
                           Consumer<Float> onChange) {
        super(name, (int) (min / step), (int) (max / step), initialValue, onChange);
        this.scale = 1f / step;
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


