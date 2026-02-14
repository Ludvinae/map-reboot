package com.yorkhuul.life.core.engine.steps;

import com.yorkhuul.life.core.world.World;

public interface WorldStep<C> {

    void apply(World world, C config);
}
