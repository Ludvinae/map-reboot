package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;

import java.util.ArrayList;
import java.util.List;

public class WaterErosion implements HydrologyStep {

    private float sedimentCapacityCoefficient;
    private float maxErosionPerStep;
    private float strength;
    List<Parameter<?>> parameters = new ArrayList<>();

    public WaterErosion(float sedimentCapacityCoefficient, float maxErosionPerStep, float strength) {
        this.sedimentCapacityCoefficient = sedimentCapacityCoefficient;
        this.maxErosionPerStep = maxErosionPerStep;
        this.strength = strength;
    }

    @Override
    public void apply(World world, EditorContext EditorContext) {
        WorldIterations.forEachTile(world, (x, y, tile) -> {
            HydrologyContext context = world.getHydrologyContext();
            int index = context.getIndex(x, y);
            float[] sediment = context.sediment;

            if (tile.getAltitude() <= world.getSeaLevel()) {
                depositAllSediment(tile, sediment, index);
                return;
            }

            float flow = context.flow[index];
            if (flow <= 0) return;


            applyErosion(tile, flow, sediment, index);
        });
    }

    private void applyErosion(Tile tile, float flow, float[] sediment, int index) {
        float capacity = flow * strength * sedimentCapacityCoefficient;

        if (sediment[index] > capacity) {
            float deposit = sediment[index] - capacity;
            sediment[index] -= deposit;
            WorldMutations.addAltitude(tile, deposit);
        }
        else {
            float erosion = Math.min(
                    capacity - sediment[index],
                    maxErosionPerStep
            );

            WorldMutations.addAltitude(tile, -erosion);
            sediment[index] += erosion;
        }
    }

    private void depositAllSediment(Tile tile, float[] sediment, int index) {
        WorldMutations.addAltitude(tile, sediment[index]);
        sediment[index] = 0;
    }

    @Override
    public String getName() {
        return "Erosion";
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return parameters;
    }
}
