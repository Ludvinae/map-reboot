package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

import java.util.Arrays;
import java.util.List;

public class HydrologyContext {
    /**
     * Snaphsot de l'etat hydrologique
     */

    public final float[] water;
    public final float[] waterBuffer;
    public final float[] sediment;
    public final float[] flow;
    public final float[] cumulativeFlow;
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

    public int getIndex(int worldX, int worldY) {
        return worldY * width + worldX;
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
