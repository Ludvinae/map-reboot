package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

import java.util.List;

public class HydrologyContext {
    /**
     * Snaphsot de l'etat hydrologique
     */

    public final float[] water;
    public final float[] sediment;
    public final float[] flow;
    private final int width;

    public HydrologyContext() {
        int height = WorldQueries.getWorldHeight();
        width = WorldQueries.getWorldWidth();
        int size = height * width;

        this.water = new float[size];
        this.sediment = new float[size];
        this.flow = new float[size];
    }


    public float getMaxFlow() {
        float maxFlow = 0;
        for (float value: flow) {
            if (value > maxFlow) maxFlow = value;
        }
        return maxFlow;
    }

    public int getIndex(int worldX, int worldY) {
        return worldY * width + worldX;
    }

}
