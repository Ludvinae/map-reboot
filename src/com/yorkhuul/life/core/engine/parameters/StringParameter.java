package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class StringParameter extends Parameter<String> {

    private final String initialValue;

    public StringParameter(
            String name,
            String initialValue,
            Consumer<String> onChange
    ) {
        super(name, 0, 0, 0, onChange);
        this.initialValue = initialValue;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void updateFromText(String text) {
        onChange.accept(text);
    }

    @Override
    protected String convert(int sliderValue) {
        throw new UnsupportedOperationException(
                "StringParameter does not support slider conversion"
        );
    }

    @Override
    protected String format(String value) {
        return value;
    }
}

