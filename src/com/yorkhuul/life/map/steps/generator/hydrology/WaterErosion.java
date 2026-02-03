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


        /*
        HydrologyContext context = world.getHydrologyContext();
        for (TileWithCoordinates tileWithCoordinates: context.getTiles()) {
            TileWithCoordinates target = tileWithCoordinates.getLowestNeighbor();
            if (target == null) continue;

            float flow = tileWithCoordinates.getFlow();
            if (flow <= 0) continue;

            float slope = tileWithCoordinates.getSlope();
            float capacity = flow * slope * sedimentCapacityCoefficient;

            Tile tile = tileWithCoordinates.getTile();
            float sediment = tile.getSediment();

            if (tile.getAltitude() <= world.getSeaLevel()) {
                // Dump les sediments au niveau de la mer
                tile.addAltitude(sediment);
                tile.setSediment(0);
            }
            else if (sediment > capacity) {
                    // Dépot de sediments
                    float deposit = sediment - capacity;
                    tile.addAltitude(deposit);
                    tile.setSediment(sediment - deposit);
            }
            else {
                // Erosion
                float erosion = Math.min(capacity - sediment, tile.getAltitude());
                erosion = Math.min(erosion, maxErosionPerStep);
                tile.addAltitude(-erosion);
                tile.setSediment(sediment + erosion);
            }

            // Transport des sediments vers l'aval
            target.getTile().addSediment(tile.getSediment());
            target.getTile().setSediment(0);
        }
        //consoleFeedback("Water Erosion ");
        */

    }

    private void applyErosion(Tile tile, float flow) {
        float capacity = flow * strength * sedimentCapacityCoefficient;

        if (tile.getSediment() > capacity) {
            float deposit = tile.getSediment() - capacity;
            WorldMutations.addAltitude(tile, deposit);
            tile.addSediment(-deposit);
        }
        else {
            float erosion = Math.min(
                    capacity - tile.getSediment(),
                    maxErosionPerStep
            );

            WorldMutations.addAltitude(tile, -erosion);
            tile.addSediment(erosion);
        }
    }

    private void depositAllSediment(Tile tile) {
        float sediment = tile.getSediment();
        WorldMutations.addAltitude(tile, sediment);
        tile.setSediment(0);
    }
}
