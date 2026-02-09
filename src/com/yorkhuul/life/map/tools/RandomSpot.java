package com.yorkhuul.life.map.tools;

import com.yorkhuul.life.map.zone.world.World;

public class RandomSpot {

    private int width;
    private int height;
    private Coordinates coords;

    public RandomSpot(World world) {
        this.width = world.getWidthInTiles();
        this.height = world.getHeightInTiles();
        coords = getSpot();
    }


    public Coordinates getCoords() {
        return coords;
    }

    public Coordinates getSpot() {
        double factorX = Math.random();
        double factorY = Math.random();
        int x = Math.toIntExact(Math.round(width * factorX));
        int y = Math.toIntExact(Math.round(height * factorY));


        return new Coordinates(x, y);
    }

}