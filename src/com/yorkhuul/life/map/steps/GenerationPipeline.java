package com.yorkhuul.life.map.steps;

import com.yorkhuul.life.map.steps.features.FeatureStep;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyStep;
import com.yorkhuul.life.map.tools.RuntimeMemoryUsage;
import com.yorkhuul.life.map.zone.world.World;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GenerationPipeline {

    private final World world;
    private HydrologyContext context;

    public GenerationPipeline(World world) {
        this.world = world;
    }

    public void runGeology(List<GenerationStep> steps, boolean debug) {
        LocalDateTime startTime = LocalDateTime.now();
        for (GenerationStep step : steps) {
            if (debug) System.out.println(RuntimeMemoryUsage.memoryUsage());
            step.apply(world);
        }
        System.out.println("Geologic cycle finished in " + getDuration(startTime) + " milliseconds.");
    }

    public void runHydrology(List<HydrologyStep> steps, boolean debug) {
        LocalDateTime startTime = LocalDateTime.now();
        if (context == null) context = new HydrologyContext();

        steps.forEach(step -> {
            if (debug) System.out.println(RuntimeMemoryUsage.memoryUsage());
            step.apply(world);
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
            step.apply(world);
        });
        System.out.println("Feature cycle finished in " + getDuration(startTime) + " milliseconds.");
    }

}
