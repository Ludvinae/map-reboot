package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.world.World;

import java.util.List;

public class StepExecution<C extends StepConfig> {

    private final GenerationStep<C> step;
    private final C config;

    public StepExecution(GenerationStep<C> step, C config) {
        this.step = step;
        this.config = config;
    }

    public void execute(World world) {
        step.apply(world, config);
    }

    public C getConfig() {
        return config;
    }

    public GenerationStep<C> getStep() {
        return step;
    }

    public List<Parameter<?>> createParameters() {
        return config.buildParameters();
    }
}

