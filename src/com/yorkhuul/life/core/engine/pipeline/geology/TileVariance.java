package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.World;

public class TileVariance implements GenerationStep<VarianceConfig> {

    @Override
    public void apply(World world, VarianceConfig config) {

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            NoiseService noise = world.getNoise();
            double factor = Math.exp(noise.sample(worldX, worldY, config.getNoiseFrequency()) * config.getNoiseFrequency());
            tile.multiplyAltitude((float) factor);
        });
    }

    @Override
    public String getName() {
        return "Altitude variance";
    }

}
