package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.shape.effect.AddEffectTarget;
import com.yorkhuul.life.map.shape.effect.EffectTarget;
import com.yorkhuul.life.map.shape.effect.ShapeEffect;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSpot;

import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class Volcanic implements GenerationStep {

    private int count;
    private int minRadius;
    private int maxRadius;
    private float strength;
    List<Parameter<?>> parameters = new ArrayList<>();

    public Volcanic(int count, int minRadius, int maxRadius, float strength) {
        this.count = count;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.strength = strength;
    }

    public Volcanic() {
        this(100, 5, 10, 0.5f);
    }

    @Override
    public void apply(World world, EditorContext context) {

        for (int i = 0; i < this.count; i++) {
            Coordinates coords = new RandomSpot(world).getCoords();

            int radius = new RandomInteger(this.minRadius, this.maxRadius).getRandomInt();
            Shape circle = new CircleShape(coords, radius, strength);
            EffectTarget effect = new AddEffectTarget();
            ShapeEffect volcanic = new ShapeEffect(circle, effect);

            world.applyShapeEffect(volcanic);
        }
        //consoleFeedback("Volcanic x " + count);
    }

    @Override
    public String getName() {
        return "Volcanic";
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return parameters;
    }
}
