package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.effect.AddEffect;
import com.yorkhuul.life.map.effect.Effect;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSpot;

import com.yorkhuul.life.map.zone.World;

public class Volcanic implements GenerationStep{

    private int count;
    private int minRadius;
    private int maxRadius;
    private float strength;

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
    public void apply(World world) {

        for (int i = 0; i < this.count; i++) {
            Coordinates coords = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();

            int radius = new RandomInteger(this.minRadius, this.maxRadius).getRandomInt();
            Shape circle = new CircleShape(coords, radius, strength);
            Effect effect = new AddEffect();
            ShapeEffect volcanic = new ShapeEffect(circle, effect);

            world.applyShapeEffect(volcanic);
        }
        consoleFeedback("Volcanic x " + count);
    }
}
