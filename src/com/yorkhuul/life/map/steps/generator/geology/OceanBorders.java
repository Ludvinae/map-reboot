package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.effect.AddEffectTarget;
import com.yorkhuul.life.map.effect.EffectTarget;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.shape.MapEdges;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.zone.world.World;

public class OceanBorders implements GenerationStep {

    private int coastWidth;
    private float strength;

    public OceanBorders(int coastWidth, float strength) {
        this.coastWidth = coastWidth;
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        Shape edges = new MapEdges(
                world.getWidthInTiles(),
                world.getHeightInTiles(),
                coastWidth,
                strength
        );
        EffectTarget effect = new AddEffectTarget();
        ShapeEffect ocean = new ShapeEffect(edges, effect);

        world.applyShapeEffect(ocean);
        //consoleFeedback("Sea border");
    }


}
