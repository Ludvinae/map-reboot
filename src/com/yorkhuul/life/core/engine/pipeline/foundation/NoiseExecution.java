package com.yorkhuul.life.core.engine.pipeline.foundation;

import com.yorkhuul.life.core.engine.pipeline.PhaseStep;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;

public class NoiseExecution extends StepExecution<NoiseConfig> {

    public NoiseExecution(PhaseStep<NoiseConfig> step, NoiseConfig config) {
        super(step, config);
    }
}
