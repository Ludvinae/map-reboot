package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class IntParameter extends SliderParameter<Integer> {

    public IntParameter(String name,
                        int min,
                        int max,
                        int initialValue,
                        Consumer<Integer> onChange) {
        super(name, min, max, initialValue, onChange);
    }

    @Override
    public Integer fromSlider(int sliderValue) {
        return sliderValue;
    }

    @Override
    public int toSlider(Integer value) {
        return value;
    }

    @Override
    public String format(Integer value) {
        return String.valueOf(value);
    }
}

