package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

import java.util.List;

import static com.yorkhuul.life.map.zone.world.WorldQueries.BUCKETS;

public class WaterFlow implements HydrologyStep {

    private float strength;

    public WaterFlow(float strength) {
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        HydrologyContext context = world.getHydrologyContext();
        if (context == null) System.out.println("No pipeline associated with this world");
        List<Coordinates>[] buckets = WorldQueries.getTilesFromBuckets(world);

        for (int b = BUCKETS - 1; b >= 0; b--) {
            for (Coordinates coords : buckets[b]) {
                applyFlowOnTile(world, context, coords.x(), coords.y());
            }
        }
        /*
        WorldIterations.forEachTile(world, (wx, wy, tile) -> {
            if (tile.getAltitude() <= world.getSeaLevel()) return;

            int index = context.getIndex(wx, wy);
            float water = context.water[index];
            if (water <= 0) return;

            TileWithCoordinates lowest = WorldQueries.getLowestAltitudeNeighbor(world, wx, wy);
            if (lowest == null) return;

            float slope = WorldQueries.getSlope(wx, wy, tile, lowest);
            if (slope <= 0) return;

            float flow = slope * water * strength;

            WorldMutations.transferWater(
                    context, wx, wy,
                    lowest.getWorldX(), lowest.getWorldY(),
                    flow
            );
            context.flow[index] = flow;
            context.cumulativeFlow[index] += flow;
        });

         */
    }

    private void applyFlowOnTile(World world, HydrologyContext context, int x, int y) {
        Tile tile = world.getTileWithWorldCoordinates(x, y);
        if (tile.getAltitude() <= world.getSeaLevel()) return;

        int index = context.getIndex(x, y);
        float water = context.water[index];
        if (water <= 0) return;

        TileWithCoordinates lowest = WorldQueries.getLowestAltitudeNeighbor(world, x, y);
        if (lowest == null) return;

        float slope = WorldQueries.getSlope(x, y, tile, lowest);
        if (slope <= 0) return;

        float flow = slope * water * strength;
        if (flow <= 0) return;

        WorldMutations.transferWater(
                context, x, y,
                lowest.getWorldX(), lowest.getWorldY(),
                flow
        );
        context.flow[index] = flow;
        context.cumulativeFlow[index] += flow;



    }

    /*
    private boolean isLake(Tile tile, List<TileWithCoordinates> neighbors) {
        float surface = tile.waterSurface();

        for (TileWithCoordinates n : neighbors) {
            if (n.getTile().waterSurface() < surface) {
                return false;
            }
        }
        return true;
    }

     */

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
