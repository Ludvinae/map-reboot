package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.world.World;

import java.util.List;

public class StepExecution<C extends StepConfig> {

    private final GenerationStep<C> step;
    private final C config;

    private boolean enabled = true;

    public StepExecution(GenerationStep<C> step, C config) {
        this.step = step;
        this.config = config;
    }

    public void execute(World world) {
        if (!enabled) return;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isOptional() {
        return step.isOptional();
    }
}

