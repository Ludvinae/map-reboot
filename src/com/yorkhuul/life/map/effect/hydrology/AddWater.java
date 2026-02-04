package com.yorkhuul.life.map.effect.hydrology;

import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;

import com.yorkhuul.life.map.zone.world.World;

public class AddWater implements HydrologyEffect {

    @Override
    public void apply(World world, int worldX, int worldY, float influence) {
        HydrologyContext context = world.getHydrologyContext();
        int index = context.getIndex(worldX, worldY);
        context.water[index] += influence;
    }
}
