package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class FloatParameter extends Parameter<Float> {

    private final float minFloat;
    private final float scale;
    private final int precision;

    public FloatParameter(
            String name,
            float minFloat,
            float maxFloat,
            float initialValue,
            float scale,
            Consumer<Float> onChange
    ) {
        super(
                name,
                0,
                (int) ((maxFloat - minFloat) / scale),
                Math.round((initialValue - minFloat) / scale),
                onChange
        );

        this.minFloat = minFloat;
        this.scale = scale;
        this.precision = countDecimals(scale);
    }

    /**
     * Method used to retieve data from UI into the config
     * @param sliderValue
     */
    @Override
    public void updateFromSlider(int sliderValue) {
        float realValue = minFloat + sliderValue * scale;
        onChange.accept(realValue);
    }

    /**
     * Method used by the UI to display the real parameter value in the slider
     * @param sliderValue
     * @return
     */
    @Override
    public String formatValue(int sliderValue) {
        float realValue = minFloat + sliderValue * scale;
        return String.format("%." + precision + "f", realValue);
    }

    /**
     * Method used by the UI to know how many decimals to display in the slider
     * @param value
     * @return
     */
    private int countDecimals(float value) {
        String text = Float.toString(value);
        int index = text.indexOf('.');
        return index < 0 ? 0 : text.length() - index - 1;
    }


    @Override
    protected Float convert(int sliderValue) {
        return minFloat + sliderValue * scale;
    }

    @Override
    protected String format(Float value) {
        return String.format("%." + precision + "f", value);
    }
}

