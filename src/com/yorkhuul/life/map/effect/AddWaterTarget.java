package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.zone.world.World;

public class AddWaterTarget implements EffectTarget{


    @Override
    public void applyTile(World world, int worldX, int worldY, float influence) {
        HydrologyContext context = world.getHydrologyContext();
        int index = context.getIndex(worldX, worldY);
        context.water[index] += influence;
    }
}
