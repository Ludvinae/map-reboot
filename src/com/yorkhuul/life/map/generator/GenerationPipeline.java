package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class GenerationPipeline {

    private World world;
    private HydrologyContext context;

    public GenerationPipeline(World world) {
        this.world = world;
    }

    public void run(List<GenerationStep> steps) {

    }

    public HydrologyContext getContext() {
        return context;
    }
}
