package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.config.hydrology.OutflowConfig;
import com.yorkhuul.life.map.context.HydrologyContext;
import com.yorkhuul.life.map.parameters.FloatParameter;
import com.yorkhuul.life.map.parameters.IntParameter;
import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;

import java.util.ArrayList;
import java.util.List;

public class WaterLevelOutflow implements HydrologyStep<OutflowConfig> {

    @Override
    public void apply(World world, OutflowConfig config) {


        for (int i = 0; i < config.getIterations(); i++) {
            outflow(world, config);
        }
    }

    private void outflow(World world, OutflowConfig config) {
        HydrologyContext context = world.getHydrologyContext();

        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            int index = context.getIndex(worldX, worldY);

            float water = context.water[index];
            if (water <= 0) return;
            float surface = tile.getAltitude() + water;

            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {
                    if (j == 0 && i == 0) continue;
                    if (water <= 0) continue;

                    int x = worldX + j;
                    int y = worldY + i;
                    if (!world.isInBounds(x, y)) continue;

                    int indexNeighbor = context.getIndex(x, y);
                    Tile neighbor = world.getTileWithWorldCoordinates(x, y);

                    float neighborSurface = neighbor.getAltitude() + context.water[indexNeighbor];
                    float distancePonderation = 1f;
                    if (i != 0 && j != 0) distancePonderation = config.getSQRT2();

                    float delta = (surface - neighborSurface) / distancePonderation;
                    if (delta <= config.getSQRT2()) continue;

                    float transfer = delta * config.getOutflowStrength();
                    transfer = Math.min(transfer, water);

                    water -= transfer;
                    surface -= transfer;
                    context.waterBuffer[index] -= transfer;
                    context.waterBuffer[indexNeighbor] += transfer;
                }
            }
        });
        // Application of the buffer
        context.applyWaterBuffer();
    }


    @Override
    public String getName() {
        return "Outflow";
    }

    @Override
    public List<Parameter<?>> createParameters(OutflowConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations count", 1, 500, config.getIterations(), config::setIterations));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, config.getOutflowStrength(), 0.01f, config::setOutflowStrength));
        parameters.add(new FloatParameter("Minimum altitude difference", 0.001f, 0.1f, config.getMinDelta(), 0.001f, config::setMinDelta));

        return parameters;
    }

}
