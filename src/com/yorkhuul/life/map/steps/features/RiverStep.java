package com.yorkhuul.life.map.steps.features;

import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

public class RiverStep implements FeatureStep{




    @Override
    public void apply(World world) {
        for (Tile tile: world.getAllTiles()) {
            float normalizedFlow = tile.getCumulativeFlow();
        }
    }
}
