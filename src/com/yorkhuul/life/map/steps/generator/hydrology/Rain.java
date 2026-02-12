package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.display.swing.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.effect.AddWaterTarget;
import com.yorkhuul.life.map.effect.EffectTarget;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomRainSpot;
import com.yorkhuul.life.map.tools.RandomSpot;
import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class Rain implements HydrologyStep {

    private int count;
    private int minRadius;
    private int maxRadius;
    private float rainfallAmount;
    List<Parameter<?>> parameters = new ArrayList<>();

    public Rain(int count, int minRadius,int maxRadius, float rainfallAmount) {
        this.count = count;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.rainfallAmount = rainfallAmount;
    }

    @Override
    public void apply(World world, EditorContext context) {

        for (int i = 0; i < count; i++) {
            Coordinates center = new RandomRainSpot(world).getCoords();
            int radius = new RandomInteger(this.minRadius, this.maxRadius).getRandomInt();

            float rainfall = (float) (Math.random() * rainfallAmount);

            Shape circle = new CircleShape(center, radius, rainfall);
            EffectTarget target = new AddWaterTarget();
            ShapeEffect rain = new ShapeEffect(circle, target);

            world.applyShapeEffect(rain);
        }
        //consoleFeedback("Rain x " + count);
    }

    @Override
    public String getName() {
        return "Rain";
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return parameters;
    }
}
