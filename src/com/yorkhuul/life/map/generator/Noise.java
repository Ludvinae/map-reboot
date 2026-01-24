package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.effect.AddEffect;
import com.yorkhuul.life.map.effect.Effect;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.shape.BlanketShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.zone.World;

public class Noise implements GenerationStep {

    private float strength;

    public Noise(float strength) {
        this.strength = strength;
    }

    @Override
    public void apply(World world) {

        Shape blanket = new BlanketShape(strength);
        Effect effect = new AddEffect();
        ShapeEffect noise = new ShapeEffect(blanket, effect);

        world.applyShapeEffect(noise);
    }
}
