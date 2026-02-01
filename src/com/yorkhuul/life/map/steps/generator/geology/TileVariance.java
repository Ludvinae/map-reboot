package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

public class TileVariance implements GenerationStep {

    private float factor;

    public TileVariance(float factor) {
        this.factor = factor;
    }


    @Override
    public void apply(World world) {
        float lower = 1 - factor;
        float higher = 1 + factor;

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            tile.multiplyAltitude((float) (lower + ((higher - lower) * Math.random())));
        });
    }
}
