package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.effect.AddWater;
import com.yorkhuul.life.map.effect.Effect;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSpot;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

public class Rain implements GenerationStep {

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

        // reset water flow, should be in another part of the program
        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            tile.setFlow(0);
        });

        for (int i = 0; i < count; i++) {
            Coordinates center = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();
            int radius = new RandomInteger(this.minRadius, this.maxRadius).getRandomInt();

            float rainfall = (float) (Math.random() * rainfallAmount);

            Shape circle = new CircleShape(center, radius, rainfall);
            Effect effect = new AddWater();
            ShapeEffect rain = new ShapeEffect(circle, effect);

            world.applyShapeEffect(rain);
        }
        consoleFeedback("Rain x " + count);
    }

}
