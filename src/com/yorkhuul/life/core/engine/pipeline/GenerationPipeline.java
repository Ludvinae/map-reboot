package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.world.World;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GenerationPipeline {

    public void run(List<StepExecution<?>> executions, World world, String type) {
        LocalDateTime startTime = LocalDateTime.now();

        for (StepExecution<?> execution : executions) {
            execution.execute(world);
        }

        System.out.println(type + " applied in " + getDuration(startTime) + " milliseconds.");
    }

    private int getDuration(LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return Math.toIntExact(duration.toMillis());
    }
}
