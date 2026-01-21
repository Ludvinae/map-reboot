package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class WorldGenerator {

    private List<GenerationStep> steps;

    public WorldGenerator(List<GenerationStep> steps) {
        this.steps = steps;
    }

    public void generate(World world) {
        for (GenerationStep step: steps) {
            step.apply(world);
        }
    }
}
