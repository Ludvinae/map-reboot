package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.engine.shape.MapEdges;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddEffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.core.world.World;

public class OceanBorders implements GenerationStep<BorderConfig> {

    @Override
    public void apply(World world, BorderConfig config) {
        Shape edges = new MapEdges(
                world.getWidthInTiles(),
                world.getHeightInTiles(),
                config.getCoastWidth(),
                config.getStrength()
        );
        EffectTarget effect = new AddEffectTarget();
        ShapeEffect ocean = new ShapeEffect(edges, effect);

        world.applyShapeEffect(ocean);
        //consoleFeedback("Sea border");
    }

    @Override
    public String getName() {
        return "Ocean borders";
    }

    @Override
    public boolean isOptional() {
        return true;
    }

}
