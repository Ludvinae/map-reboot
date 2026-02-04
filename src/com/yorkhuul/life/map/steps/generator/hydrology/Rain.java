package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.effect.hydrology.AddWater;
import com.yorkhuul.life.map.effect.Effect;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.effect.hydrology.HydrologyEffect;
import com.yorkhuul.life.map.effect.hydrology.ShapeHydrologyEffect;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSpot;
import com.yorkhuul.life.map.zone.world.World;

public class Rain implements HydrologyStep {

    private int count;
    private int minRadius;
    private int maxRadius;
    private float rainfallAmount;

    public Rain(int count, int minRadius,int maxRadius, float rainfallAmount) {
        this.count = count;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.rainfallAmount = rainfallAmount;
    }

    @Override
    public void apply(World world) {

        for (int i = 0; i < count; i++) {
            Coordinates center = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();
            int radius = new RandomInteger(this.minRadius, this.maxRadius).getRandomInt();

            float rainfall = (float) (Math.random() * rainfallAmount);

            Shape circle = new CircleShape(center, radius, rainfall);
            HydrologyEffect effect = new AddWater();
            ShapeHydrologyEffect rain = new ShapeHydrologyEffect(circle, effect);

            world.applyShapeHydrologyEffect(rain);
        }
        //consoleFeedback("Rain x " + count);
    }

}
