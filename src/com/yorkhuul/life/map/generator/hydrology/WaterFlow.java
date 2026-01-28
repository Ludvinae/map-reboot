package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.tools.TileWithCoordinates;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

import java.util.Comparator;
import java.util.List;

public class WaterFlow implements GenerationStep {

    private int count;
    private float seaLevel;

    public WaterFlow(int count, float seaLevel) {
        this.count = count;
        this.seaLevel = seaLevel;
    }

    @Override
    public void apply(World world) {
        world.computeLowestTileNeighbor();
        List<Tile> tiles = tilesSorted(world);

        for (int i = 0; i < count; i++) {
            flow(tiles);
        }
        consoleFeedback("Water flow x " + count);
    }

    private void flow(List<Tile> tiles) {
        for (Tile tile: tiles) {
            // early return à enlever si implementation erosion fluviale ou re-injection d'eau !!!
            // les tiles situées sous le niveau de la mer ne ruissellent pas
            // mais si l'altitude change en cours de route cette optimisation deviens obsolete
            // dans ce cas, remplacr return par continue
            if (tile.getAltitude() <= seaLevel) return;

            TileWithCoordinates neighbor = tile.getFlowTarget();
            if (neighbor == null) continue;

            float waterFlow = tile.getWater();
            neighbor.tile().addWater(waterFlow);
            tile.addFlow(waterFlow);
            tile.setWater(0);
            //System.out.println(tile.getFlow());
        }
    }


    private List<Tile> tilesSorted(World world) {
        List<Tile> tiles = world.getAllTiles();
        tiles.sort(
                Comparator.comparing(Tile::getAltitude).reversed()
        );

        return tiles;
    }


}
