package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.shape.MapEdges;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.zone.World;

public class OceanBorders implements GenerationStep{

    private int coastWidth;
    private float strength;

    public OceanBorders(int coastWidth, float strength) {
        this.coastWidth = coastWidth;
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        Shape ocean = new MapEdges(
                world.getWidthInTiles(),
                world.getHeightInTiles(),
                coastWidth,
                strength
        );

        world.applyShape(ocean);
    }


}
