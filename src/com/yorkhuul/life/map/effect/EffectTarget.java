package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.zone.world.World;

public interface EffectTarget {

    void applyTile(World world, int worldX, int worldY, float influence);
}
