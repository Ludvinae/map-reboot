package com.yorkhuul.life.map.parameters;

import java.util.function.Consumer;

public class IntParameter extends Parameter<Integer> {

    public IntParameter(String name, int min, int max, int initial, Consumer<Integer> onChange) {
        super(name, min, max, initial, onChange);
    }

    @Override
    protected Integer convert(int sliderValue) {
        return sliderValue;
    }

    @Override
    protected String format(Integer value) {
        return Integer.toString(value);
    }
}
