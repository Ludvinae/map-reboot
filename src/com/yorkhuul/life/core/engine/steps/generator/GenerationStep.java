package com.yorkhuul.life.core.engine.steps.generator;

import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.steps.WorldStep;

import java.util.List;


public interface GenerationStep<C> extends WorldStep<C> {

    String getName();

    List<Parameter<?>> createParameters(C config);

}
