package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.generator.hydrology.HydrologyStep;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class GenerationPipeline {

    private World world;
    private HydrologyContext context;

    public GenerationPipeline(World world) {
        this.world = world;
    }

    public void runGeology(List<GenerationStep> steps) {
        for (GenerationStep step : steps) {
            step.apply(world);
        }
    }

    public void runHydrology(List<HydrologyStep> steps) {
        context = world.getTilesContext();
        steps.forEach(step -> step.apply(world));
        context = null;
    }

    public HydrologyContext getContext() {
        if (context == null) context = world.getTilesContext();
        return context;
    }


}
