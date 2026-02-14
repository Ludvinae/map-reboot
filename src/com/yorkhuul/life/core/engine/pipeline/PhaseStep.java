package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.engine.parameters.Parameter;

import java.util.List;


public interface PhaseStep<C> extends GenerationStep<C> {

    String getName();

    List<Parameter<?>> createParameters(C config);

}
