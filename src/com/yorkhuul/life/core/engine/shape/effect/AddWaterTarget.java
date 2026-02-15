package com.yorkhuul.life.core.engine.shape.effect;

import com.yorkhuul.life.core.engine.pipeline.hydrology.HydrologyContext;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.WorldQueries;
import com.yorkhuul.life.utils.position.ArraytoMatrixIndex;

public class AddWaterTarget implements EffectTarget{


    @Override
    public void applyTile(World world, int worldX, int worldY, float influence) {
        HydrologyContext context = world.getHydrologyContext();
        int index = ArraytoMatrixIndex.getIndex(worldX, worldY, WorldQueries.getWorldWidth());
        context.water[index] += influence;
    }
}
