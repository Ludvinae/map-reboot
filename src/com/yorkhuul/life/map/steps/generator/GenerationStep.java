package com.yorkhuul.life.map.steps.generator;

import com.yorkhuul.life.map.steps.WorldStep;


public interface GenerationStep extends WorldStep {


    default void consoleFeedback(String operation) {
        System.out.println("Operation (" + operation + ") finished");
    };
}
