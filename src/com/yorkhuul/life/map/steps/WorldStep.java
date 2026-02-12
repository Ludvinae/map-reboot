package com.yorkhuul.life.map.steps;

import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.context.config.StepConfig;
import com.yorkhuul.life.map.zone.world.World;

public interface WorldStep<C> {

    void apply(World world, C config);
}
