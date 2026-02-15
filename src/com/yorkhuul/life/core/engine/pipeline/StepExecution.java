package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.world.World;

public class StepExecution<C extends StepConfig> {

    private final PhaseStep<C> step;
    private final C config;

    public StepExecution(PhaseStep<C> step, C config) {
        this.step = step;
        this.config = config;
    }

    public void execute(World world) {
        step.apply(world, config);
    }

    public C getConfig() {
        return config;
    }

    public PhaseStep<C> getStep() {
        return step;
    }
}

