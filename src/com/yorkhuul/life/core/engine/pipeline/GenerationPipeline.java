package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.engine.context.EditorContext;
import com.yorkhuul.life.core.engine.context.HydrologyContext;
import com.yorkhuul.life.core.engine.pipeline.features.FeatureStep;
import com.yorkhuul.life.core.engine.pipeline.geology.NoiseConfig;
import com.yorkhuul.life.core.engine.pipeline.hydrology.HydrologyStep;
import com.yorkhuul.life.utils.misc.RuntimeMemoryUsage;
import com.yorkhuul.life.core.world.World;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GenerationPipeline {

    private final World world;
    private HydrologyContext context;
    private EditorContext editorContext;

    public GenerationPipeline(EditorContext editorContext) {
        this.world = editorContext.getWorld();
        this.editorContext = editorContext;
    }

    public void runNoise(GenerationStep<NoiseConfig> step, boolean debug) {
        LocalDateTime startTime = LocalDateTime.now();
        NoiseConfig config = editorContext.getNoiseConfig();
        //System.out.println(config);
        if (debug) System.out.println(RuntimeMemoryUsage.memoryUsage());
        step.apply(world, config);
        System.out.println("Noise applied in " + getDuration(startTime) + " milliseconds.");
    }

    public void runGeology(List<GenerationStep<StepConfig>> steps, boolean debug) {
        LocalDateTime startTime = LocalDateTime.now();
        List<StepConfig> configs = editorContext.getStepConfigs();
        for (int i = 0; i < steps.size(); i++) {
            GenerationStep<StepConfig> step = steps.get(i);
            StepConfig stepConfig = configs.get(i);

            if (debug) System.out.println(RuntimeMemoryUsage.memoryUsage());
            step.apply(world, stepConfig);
        }
        System.out.println("Geologic cycle finished in " + getDuration(startTime) + " milliseconds.");
    }

    public void runHydrology(List<HydrologyStep> steps, boolean debug) {
        LocalDateTime startTime = LocalDateTime.now();
        if (context == null) context = new HydrologyContext();

        steps.forEach(step -> {
            if (debug) System.out.println(RuntimeMemoryUsage.memoryUsage());
            step.apply(world, editorContext);
        });
        System.out.println("Hydrologic cycle finished in " + getDuration(startTime) + " milliseconds.");
    }

    public HydrologyContext getContext() {
        return context;
    }

    private int getDuration(LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return Math.toIntExact(duration.toMillis());
    }

    public void runFeatures(List<FeatureStep> steps, boolean debug) {
        LocalDateTime startTime = LocalDateTime.now();
        steps.forEach(step -> {
            if (debug) System.out.println(RuntimeMemoryUsage.memoryUsage());
            step.apply(world, editorContext);
        });
        System.out.println("Feature cycle finished in " + getDuration(startTime) + " milliseconds.");
    }

}
