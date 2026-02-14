package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public abstract class Parameter<T> {

    protected final String name;
    protected final int min;
    protected final int max;
    protected final int initial;

    protected final Consumer<T> onChange;

    public Parameter(String name, int min, int max, int initial, Consumer<T> onChange) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.initial = initial;
        this.onChange = onChange;
    }

    public String getName() { return name; }
    public int getMin() { return min; }
    public int getMax() { return max; }
    public int getInitial() { return initial; }

    public void updateFromSlider(int sliderValue) {
        T value = convert(sliderValue);
        onChange.accept(value);
    }

    public String formatValue(int sliderValue) {
        return format(convert(sliderValue));
    }

    protected abstract T convert(int sliderValue);
    protected abstract String format(T value);
}

