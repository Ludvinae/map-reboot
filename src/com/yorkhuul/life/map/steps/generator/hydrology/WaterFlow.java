package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.zone.tile.RiverData;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

import java.util.Comparator;
import java.util.List;

public class WaterFlow implements HydrologyStep {

    private float strength;

    public WaterFlow(float strength) {
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        /*

        // Initialisation du flow local
        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            if (tile.getAltitude() > world.getSeaLevel()) {
                tile.setCumulativeFlow(tile.getWater()); // create RiverData if it's null in Tile and set it to the amount of water
            }
        });

        // Tri des tiles par ordre decroissant d'altitude
        HydrologyContext tiles = tilesSorted(world.getHydrologyContext());

        // propagation du flow
        for (TileWithCoordinates tileWC : tiles.getTiles()) {

            Tile tile = tileWC.getTile();
            RiverData river = tile.getRiver();

            if (tile.getAltitude() <= world.getSeaLevel()) {
                continue; // la mer absorbe tout
            }

            if (river.getCumulativeFlow() <= 0) {
                continue;
            }

            List<TileWithCoordinates> neighbors = world.getNeighbors(tileWC.getWorldX(), tileWC.getWorldY());

            if (isLake(tile, neighbors)) {

                TileWithCoordinates outlet = findOutlet(tile, neighbors);
                river.setOutlet(outlet);

                if (outlet != null) {
                    outlet.getTile().addCumulativeFlow(river.getCumulativeFlow());
                }

            } else {

                TileWithCoordinates down = tileWC.getLowestNeighbor();
                if (down != null) {
                    down.getTile().addCumulativeFlow(river.getCumulativeFlow());
                }
            }
        }
        tiles.computeMaxFlow();

         */
        HydrologyContext context = world.getHydrologyContext();
        if (context == null) System.out.println("No pipeline associated with this world");

        WorldIterations.forEachTile(world, (wx, wy, tile) -> {
            if (tile.getAltitude() <= world.getSeaLevel()) return;
            if (tile.getWater() <= 0) return;

            TileWithCoordinates lowest = WorldQueries.getLowestAltitudeNeighbor(world, wx, wy);
            if (lowest == null) return;

            float slope = WorldQueries.getSlope(wx, wy, tile, lowest);
            if (slope <= 0) return;

            float flow = slope * tile.getWater() * strength;

            WorldMutations.transferWater(
                world, wx, wy,
                lowest.getWorldX(), lowest.getWorldY(),
                flow
            );

            tile.addCumulativeFlow(flow);

            assert context != null;
            if (context.getMaxFlow() < flow) context.setMaxFlow(flow);
        });


    }

    /*
    private HydrologyContext tilesSorted(HydrologyContext context) {
        context.getTiles().sort(
                Comparator.comparing(TileWithCoordinates::getAltitude).reversed()
        );
        return context;
    }

     */

    private boolean isLake(Tile tile, List<TileWithCoordinates> neighbors) {
        float surface = tile.waterSurface();

        for (TileWithCoordinates n : neighbors) {
            if (n.getTile().waterSurface() < surface) {
                return false;
            }
        }
        return true;
    }

    private TileWithCoordinates findOutlet(Tile tile, List<TileWithCoordinates> neighbors) {
        TileWithCoordinates outlet = null;
        float minAltitude = tile.getAltitude();

        for (TileWithCoordinates n : neighbors) {
            float a = n.getTile().getAltitude();
            if (a < minAltitude) {
                minAltitude = a;
                outlet = n;
            }
        }
        return outlet;
    }


}
