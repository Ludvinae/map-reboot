package com.yorkhuul.life.map.parameters;

import java.util.function.Consumer;

public class LogParameter extends Parameter<Float> {

    private final float minReal;
    private final float maxReal;

    public LogParameter(
            String name,
            int min,
            int max,
            int initial,
            float minReal,
            float maxReal,
            Consumer<Float> onChange
    ) {
        super(name, min, max, initial, onChange);
        this.minReal = minReal;
        this.maxReal = maxReal;
    }

    @Override
    protected Float convert(int sliderValue) {
        float t = (sliderValue - min) / (float)(max - min);
        return (float)(minReal * Math.pow(maxReal / minReal, t));
    }

    @Override
    protected String format(Float value) {
        return String.format("%.4f", value);
    }
}

