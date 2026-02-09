package com.yorkhuul.life.map.tools;

import com.yorkhuul.life.map.zone.world.World;

public class RandomRainSpot {

    private World world;
    private int width;
    private int height;
    private Coordinates coords;

    public RandomRainSpot(World world) {
        this.width = world.getWidthInTiles();
        this.height = world.getHeightInTiles();
        this.world = world;
        coords = getSpot();
    }


    public Coordinates getCoords() {
        return coords;
    }

    public Coordinates getSpot() {
        double factorX = Math.random();
        double factorY = Math.random();

        for (int i = 0; i < 10; i++) { // évite boucle infinie
            int x = Math.toIntExact(Math.round((width - 1) * factorX));
            int y = Math.toIntExact(Math.round((height - 1) * factorY));

            float altitude = world.getTileWithWorldCoordinates(x, y).getAltitude();

            float probability = world.getNoise().sampleFromZeroToOne(x, y, 0.01f, altitude);
            if (Math.random() < probability) {
                return new Coordinates(x, y);
            }
        }
        // fallback if no coordinates found in the 10 first attempts
        int x = Math.toIntExact(Math.round(width * factorX));
        int y = Math.toIntExact(Math.round(height * factorY));
        return new Coordinates(x, y);
    }

}
