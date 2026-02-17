package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class CheckParameter extends Parameter<Boolean> {

    public CheckParameter(String name,
                          Boolean initialValue,
                          Consumer<Boolean> onChange) {
        super(name, initialValue, onChange);
    }
}
