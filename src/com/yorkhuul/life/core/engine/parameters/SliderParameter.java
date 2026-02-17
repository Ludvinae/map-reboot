package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public abstract class SliderParameter<T> extends Parameter<T> {

    protected final int min;
    protected final int max;

    public SliderParameter(String name,
                           int min,
                           int max,
                           T initialValue,
                           Consumer<T> onChange) {
        super(name, initialValue, onChange);
        this.min = min;
        this.max = max;
    }

    public int getMin() { return min; }
    public int getMax() { return max; }

    public abstract T fromSlider(int sliderValue);

    public abstract int toSlider(T value);

    public abstract String format(T value);
}
