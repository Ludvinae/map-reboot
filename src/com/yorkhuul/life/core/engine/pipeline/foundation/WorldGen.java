package com.yorkhuul.life.core.engine.pipeline.foundation;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.world.World;

public class WorldGen implements GenerationStep<WorldConfig> {
    @Override
    public void apply(World world, WorldConfig config) {

    }

    @Override
    public String getName() {
        return "World generation";
    }
}
