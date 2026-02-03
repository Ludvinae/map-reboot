package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;

import java.util.List;

public class HydrologyContext {

    //private List<TileWithCoordinates> tiles;
    private float maxFlow = 0f;
    private float maxCumulativeFlow;

    public HydrologyContext(float maxFlow) {
        this.maxFlow = maxFlow;
        this.maxCumulativeFlow = 0;
    }

    public HydrologyContext() {
        this(0f);
    }

    /*
    public List<TileWithCoordinates> getTiles() {
        return tiles;
    }

     */

    public void setMaxFlow(float maxFlow) {
        this.maxFlow = maxFlow;
    }

    public float getMaxFlow() {
        return maxFlow;
    }

    public float getMaxCumulativeFlow(World world) {
        if (maxFlow == 0) computeMaxCumulativeFlow(world);
        return maxFlow;
    }

    public void computeMaxCumulativeFlow(World world) {
        WorldIterations.forEachTile(world, (wx, wy, tile) -> {
            float flow = tile.getCumulativeFlow();
            if (flow > maxFlow) maxCumulativeFlow = flow;
        });
    }

    /*
    public void resetFlow() {
        for (TileWithCoordinates tile: tiles) {
            tile.setFlow(0);
        }
    }

     */
}
