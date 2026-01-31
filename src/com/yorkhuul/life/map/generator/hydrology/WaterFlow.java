package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.zone.TileWithCoordinates;
import com.yorkhuul.life.map.zone.World;

import java.util.Comparator;
import java.util.List;

public class WaterFlow implements HydrologyStep {

    private int count;
    private float seaLevel;

    public WaterFlow(int count, float seaLevel) {
        this.count = count;
        this.seaLevel = seaLevel;
    }

    @Override
    public void apply(World world) {
        HydrologyContext tiles = tilesSorted(world.getHydrologyContext());

        for (int i = 0; i < count; i++) {
            flow(tiles);
        }
        consoleFeedback("Water flow x " + count);
    }

    private void flow(HydrologyContext context) {
        for (TileWithCoordinates tile: context.getTiles()) {
            if (tile.getAltitude() <= seaLevel) continue;

            TileWithCoordinates neighbor = tile.getLowestNeighbor();
            if (neighbor == null) continue;

            float waterFlow = tile.getWater();
            neighbor.getTile().addWater(waterFlow);
            tile.addFlow(waterFlow);
            tile.getTile().setWater(0);
            //System.out.println(tile.getFlow());
        }
    }


    private HydrologyContext tilesSorted(HydrologyContext context) {
        context.getTiles().sort(
                Comparator.comparing(TileWithCoordinates::getAltitude).reversed()
        );
        return context;
    }


}
