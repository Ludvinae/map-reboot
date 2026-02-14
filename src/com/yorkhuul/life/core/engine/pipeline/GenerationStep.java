package com.yorkhuul.life.core.engine.pipeline;

import com.yorkhuul.life.core.world.World;

public interface GenerationStep<C> {

    void apply(World world, C config);
}
