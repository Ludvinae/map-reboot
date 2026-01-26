package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.World;

public interface GenerationStep {

    void apply(World world);

    default void consoleFeedback(String operation) {
        System.out.println("Operation (" + operation + ") finished");
    };
}
