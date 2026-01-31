package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.zone.TileWithCoordinates;

import java.util.List;

public class HydrologyContext {

    private List<TileWithCoordinates> tiles;

    public HydrologyContext(List<TileWithCoordinates> tiles) {
        this.tiles = tiles;
    }

    public List<TileWithCoordinates> getTiles() {
        return tiles;
    }

    public void resetFlow() {
        for (TileWithCoordinates tile: tiles) {
            tile.setFlow(0);
        }
    }
}
