package com.yorkhuul.life.core.engine.pipeline.foundation;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.utils.libraries.FastNoiseLite.*;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.World;

public class Noise implements GenerationStep<NoiseConfig> {

    @Override
    public void apply(World world, NoiseConfig config) {
        NoiseService noise = world.getNoise();
        noise.setFractalType(FractalType.None);
        noise.setLacunarity(config.getLacunarity());
        noise.setOctaves(config.getNbOctaves());

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            float value = noise.sampleOffset(worldX, worldY, config.getFrequency(), config.getOffset());
            //System.out.println(value);
            Tile tile = region.getTile(localX, localY);
            tile.setAltitude(value * config.getStrength());
            //System.out.println(tile.getAltitude());
        });
        //consoleFeedback("Noise");
    }

    @Override
    public String getName() {
        return "Noise";
    }

}
