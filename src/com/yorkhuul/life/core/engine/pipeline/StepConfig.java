package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.engine.parameters.Parameter;

import java.util.List;

public interface StepConfig {
    List<Parameter<?>> createParameters();
}
