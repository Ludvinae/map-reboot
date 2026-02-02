package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;

import java.util.List;

public class HydrologyContext {

    private List<TileWithCoordinates> tiles;
    private float maxFlow = 0f;

    public HydrologyContext(List<TileWithCoordinates> tiles) {
        this.tiles = tiles;
    }


    public List<TileWithCoordinates> getTiles() {
        return tiles;
    }

    public void setMaxFlow(float maxFlow) {
        this.maxFlow = maxFlow;
    }

    public float getMaxFlow() {
        if (maxFlow == 0) computeMaxFlow();
        return maxFlow;
    }

    public void computeMaxFlow() {
        float maxFlow = 0;
        for (TileWithCoordinates tile : tiles) {
            float flow = tile.getFlow();
            if (flow > maxFlow) maxFlow = flow;
        }
        setMaxFlow(maxFlow);

    }

    public void resetFlow() {
        for (TileWithCoordinates tile: tiles) {
            tile.setFlow(0);
        }
    }
}
