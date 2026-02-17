package com.yorkhuul.life.core.engine.parameters;

import java.util.function.Consumer;

public class StringParameter extends Parameter<String> {

    public StringParameter(String name,
                           String initialValue,
                           Consumer<String> onChange) {
        super(name, initialValue, onChange);
    }
}


