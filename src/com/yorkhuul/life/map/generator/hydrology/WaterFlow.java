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
        HydrologyContext tiles = tilesSorted(world.getTilesContext());

        for (int i = 0; i < count; i++) {
            flow(tiles);
        }
        consoleFeedback("Water flow x " + count);
    }

    private void flow(HydrologyContext context) {
        for (TileWithCoordinates tile: context.getTiles()) {
            // early return à enlever si implementation erosion fluviale ou re-injection d'eau !!!
            // les tiles situées sous le niveau de la mer ne ruissellent pas
            // mais si l'altitude change en cours de route cette optimisation deviens obsolete
            // dans ce cas, remplacr return par continue
            if (tile.getAltitude() <= seaLevel) return;

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
