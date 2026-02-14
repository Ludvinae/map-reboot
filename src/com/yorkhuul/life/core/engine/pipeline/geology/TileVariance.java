package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.LogParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Parameter<?>> createParameters(VarianceConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new LogParameter("Variance noise frequency", 0.00001f, 0.1f, config.getNoiseFrequency(), 500, config::setNoiseFrequency));
        parameters.add(new FloatParameter("Altitude difference amplitude", 0.01f, 1f, config.getAmplitude(), 0.01f, config::setAmplitude));

        return parameters;
    }


}
