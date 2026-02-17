package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public abstract class Parameter<T> {
    protected final String name;
    protected T value;
    protected final Consumer<T> onChange;

    public Parameter(String name, T initialValue, Consumer<T> onChange) {
        this.name = name;
        this.value = initialValue;
        this.onChange = onChange;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        if (onChange != null) {
            onChange.accept(value);
        }
    }
}

