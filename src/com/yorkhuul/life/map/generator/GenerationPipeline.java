package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.generator.hydrology.HydrologyStep;
import com.yorkhuul.life.map.zone.World;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GenerationPipeline {

    private World world;
    private HydrologyContext context;

    public GenerationPipeline(World world) {
        this.world = world;
    }

    public void runGeology(List<GenerationStep> steps) {
        LocalDateTime startTime = LocalDateTime.now();
        for (GenerationStep step : steps) {
            step.apply(world);
        }
        System.out.println("Geologic cycle finished in " + getDuration(startTime) + " seconds.");
    }

    public void runHydrology(List<HydrologyStep> steps) {
        LocalDateTime startTime = LocalDateTime.now();
        context = world.getTilesContext();
        steps.forEach(step -> step.apply(world));
        context = null;
        System.out.println("Hydrologic cycle finished in " + getDuration(startTime) + " seconds.");
    }

    public HydrologyContext getContext() {
        if (context == null) context = world.getTilesContext();
        return context;
    }

    private int getDuration(LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        return Math.toIntExact(duration.getSeconds());
    }

}
