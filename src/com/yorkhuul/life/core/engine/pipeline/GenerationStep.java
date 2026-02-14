package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.engine.parameters.Parameter;

import java.util.List;


public interface GenerationStep<C> extends WorldStep<C> {

    String getName();

    List<Parameter<?>> createParameters(C config);

}
