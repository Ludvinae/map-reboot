package com.yorkhuul.life.core.engine.shape.effect;

import com.yorkhuul.life.core.world.World;

public interface EffectTarget {

    void applyTile(World world, int worldX, int worldY, float influence);
}
