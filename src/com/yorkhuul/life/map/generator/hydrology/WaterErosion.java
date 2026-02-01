package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.TileWithCoordinates;
import com.yorkhuul.life.map.zone.World;

public class WaterErosion implements HydrologyStep {

    private float sedimentCapacityCoefficient;
    private float maxErosionPerStep;

    public WaterErosion(float sedimentCapacityCoefficient, float maxErosionPerStep) {
        this.sedimentCapacityCoefficient = sedimentCapacityCoefficient;
        this.maxErosionPerStep = maxErosionPerStep;
    }

    @Override
    public void apply(World world) {
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
    }
}
