package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

public class WaterErosion implements HydrologyStep {

    private float sedimentCapacityCoefficient;
    private float maxErosionPerStep;
    private float strength;

    public WaterErosion(float sedimentCapacityCoefficient, float maxErosionPerStep, float strength) {
        this.sedimentCapacityCoefficient = sedimentCapacityCoefficient;
        this.maxErosionPerStep = maxErosionPerStep;
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        WorldIterations.forEachTile(world, (x, y, tile) -> {

            //Tile tile = world.getTileWithWorldCoordinates(x, y);

            if (tile.getAltitude() <= world.getSeaLevel()) {
                depositAllSediment(tile);
                return;
            }

            float flow = tile.getRiver().getCumulativeFlow();
            if (flow <= 0) return;

            applyErosion(tile, flow);
        });
    }

    private void applyErosion(Tile tile, float flow) {
        float capacity = flow * strength * sedimentCapacityCoefficient;

        if (tile.getSediment() > capacity) {
            float deposit = tile.getSediment() - capacity;
            WorldMutations.addAltitude(tile, deposit);
            WorldMutations.addSediment(tile, -deposit);
        }
        else {
            float erosion = Math.min(
                    capacity - tile.getSediment(),
                    maxErosionPerStep
            );

            WorldMutations.addAltitude(tile, -erosion);
            WorldMutations.addSediment(tile, erosion);
        }
    }

    private void depositAllSediment(Tile tile) {
        float sediment = tile.getSediment();
        WorldMutations.addAltitude(tile, sediment);
        WorldMutations.setSediment(tile,0);
    }
}
