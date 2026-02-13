package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.config.geology.VarianceConfig;
import com.yorkhuul.life.map.parameters.FloatParameter;
import com.yorkhuul.life.map.parameters.LogParameter;
import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;

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
