package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class EnumParameter<T extends Enum<T>> extends Parameter<T> {

    private final Class<T> enumClass;

    public EnumParameter(String name,
                         Class<T> enumClass,
                         T initialValue,
                         Consumer<T> onChange) {
        super(name, initialValue, onChange);
        this.enumClass = enumClass;
    }

    public T[] getValues() {
        return enumClass.getEnumConstants();
    }
}

