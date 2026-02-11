package com.yorkhuul.life.display.swing.parameters;

import java.util.function.Consumer;

public class FloatParameter extends Parameter<Float> {

    private final float scale;
    private final int precision;

    public FloatParameter(
            String name,
            int min,
            int max,
            int initial,
            float scale,
            int precision,
            Consumer<Float> onChange
    ) {
        super(name, min, max, initial, onChange);
        this.scale = scale;
        this.precision = precision;
    }

    @Override
    protected Float convert(int sliderValue) {
        return sliderValue / scale;
    }

    @Override
    protected String format(Float value) {
        return String.format("%." + precision + "f", value);
    }
}

