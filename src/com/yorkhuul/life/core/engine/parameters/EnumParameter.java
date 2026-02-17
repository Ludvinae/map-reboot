package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class EnumParameter<T extends Enum<T>> extends Parameter<T> {

    private final Class<T> enumClass;
    private T value;

    public EnumParameter(String name, Class<T> enumClass, T initialValue, Consumer<T> onChange) {
        super(name, 0, 0, 0, onChange);
        this.enumClass = enumClass;
        this.value = initialValue;
    }

    public Class<T> getEnumClass() {
        return enumClass;
    }

    public T[] getValues() {
        return enumClass.getEnumConstants();
    }

    public T getValue() {
        return value;
    }

    public void updateFromSelection(T selected) {
        this.value = selected;
    }

    @Override
    protected T convert(int sliderValue) {
        return null;
    }

    @Override
    protected String format(T value) {
        return "";
    }
}
