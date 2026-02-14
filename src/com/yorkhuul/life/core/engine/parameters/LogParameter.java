package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class LogParameter extends Parameter<Float> {

    private final float minReal;
    private final float maxReal;
    private final int scale;
    private final int precision;

    public LogParameter(
            String name,
            float minReal,
            float maxReal,
            float initialValue,
            int scale,
            Consumer<Float> onChange
    ) {
        super(
                name,
                0,
                scale,
                computeInitialSliderValue(minReal, maxReal, initialValue, scale),
                onChange
        );

        this.minReal = minReal;
        this.maxReal = maxReal;
        this.scale = scale;
        this.precision = 5; // configurable si besoin
    }

    @Override
    protected Float convert(int sliderValue) {
        float t = sliderValue / (float) scale;
        return (float) (minReal * Math.pow(maxReal / minReal, t));
    }

    @Override
    protected String format(Float value) {
        return String.format("%." + precision + "f", value);
    }

    private static int computeInitialSliderValue(
            float minReal,
            float maxReal,
            float initialValue,
            int steps
    ) {
        if (minReal <= 0 || maxReal <= 0)
            throw new IllegalArgumentException("Log scale requires positive values");

        float ratio = initialValue / minReal;
        float logRatio = (float) (Math.log(ratio) / Math.log(maxReal / minReal));
        return Math.round(logRatio * steps);
    }
}

