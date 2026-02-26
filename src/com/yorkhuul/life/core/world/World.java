package com.yorkhuul.life.core.world;

import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.utils.position.Coordinates;

/**
 * Store physical state of the world, survive between phases.
 * It's the source of truth.
 */
public class World {

    //private final WorldConfig worldConfig;

    private String name;
    private String seed;
    private int width;
    private int height;
    private int size;

    private float[] altitude;
    private float[] water;


    public World(WorldConfig worldConfig) {
        //this.worldConfig = worldConfig;
        init(worldConfig);
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public String getSeed() {
        return seed;
    }

    private void init(WorldConfig worldConfig) {
        this.name = worldConfig.getName();
        this.seed = worldConfig.getSeed();
        this.width = worldConfig.getWidth();
        this.height = worldConfig.getHeight();
        this.size = width * height;

        this.altitude = new float[size];
        this.water = new float[size];
    }

    public int getIndex(int x, int y) {
        return y * width + x;
    }

    public Coordinates getCoordinates(int index) {
        int x = index % width;
        int y = index / width;
        return new Coordinates(x, y);
    }
}
