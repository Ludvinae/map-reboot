package com.yorkhuul.life.map.effect.hydrology;

import com.yorkhuul.life.map.zone.world.World;

public interface HydrologyEffect{

    void apply(World world, int worldX, int worldY, float influence);
}
