package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.tile.TileWithCoordinates;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.WorldQueries;

import java.util.Arrays;

public class HydrologyContext {
    /**
     * Snaphsot de l'etat hydrologique
     */

    public final float[] water;
    public final float[] waterBuffer;
    public final float[] sediment;
    public final float[] flow;
    public final float[] cumulativeFlow;
    public final float[] riverWidth;
    public final int[] outNeighbor;
    private final int width;
    private final int height;

    public HydrologyContext() {
        height = WorldQueries.getWorldHeight();
        width = WorldQueries.getWorldWidth();
        int size = height * width;

        this.water = new float[size];
        this.waterBuffer = new float[size];
        this.sediment = new float[size];
        this.flow = new float[size];
        this.cumulativeFlow = new float[size];
        this.riverWidth = new float[size];
        this.outNeighbor = new int[size];

    }


    public float getMaxFlow() {
        float maxFlow = 0;
        for (float value: flow) {
            if (value > maxFlow) maxFlow = value;
        }
        return maxFlow;
    }

    public float getMaxCumulativeFlow() {
        float maxCumulativeFlow = 0;
        for (float value: cumulativeFlow) {
            if (value > maxCumulativeFlow) maxCumulativeFlow = value;
        }
        return maxCumulativeFlow;
    }

    public TileWithCoordinates getTileWithCoordinatesFromIndex(World world, int index) {
        int x = index % width;
        int y = index / width;
        Tile tile = world.getTileWithWorldCoordinates(x, y);
        return new TileWithCoordinates(tile, x, y);
    }

    public void clearWaterBuffer() {
        Arrays.fill(waterBuffer, 0f);
    }

    public void applyWaterBuffer() {
        for (int i = 0; i < height * width; i++) {
            water[i] += waterBuffer[i];
        }
        clearWaterBuffer();
    }
}
