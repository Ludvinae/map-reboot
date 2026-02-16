package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class CheckParameter extends Parameter<Boolean> {

    private final boolean initialValue;

    public CheckParameter(String name, boolean initialValue, Consumer<Boolean> onChange) {
        super(name, 0, 0, 0 , onChange);
        this.initialValue = initialValue;
    }

    public boolean getInitialValue() {
        return initialValue;
    }

    public void updateFromCheck(boolean value) {
        onChange.accept(value);
    }

    @Override
    protected Boolean convert(int sliderValue) {
        throw new UnsupportedOperationException("CheckParameter does not support slider conversion");
    }

    @Override
    protected String format(Boolean value) {
        return value.toString();
    }
}
