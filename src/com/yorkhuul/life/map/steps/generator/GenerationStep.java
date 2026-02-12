package com.yorkhuul.life.map.steps.generator;

import com.yorkhuul.life.display.swing.parameters.Parameter;
import com.yorkhuul.life.map.steps.WorldStep;

import java.util.List;


public interface GenerationStep extends WorldStep {

    String getName();

    List<Parameter<?>> getParameters();

}
