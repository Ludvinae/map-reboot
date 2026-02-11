package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;
import com.yorkhuul.life.map.zone.world.WorldMutations;
import com.yorkhuul.life.map.zone.world.WorldQueries;

import java.util.ArrayList;
import java.util.Arrays;
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

        Arrays.fill(context.flow, 0f);
        Arrays.fill(context.cumulativeFlow, 0f);
        List<Coordinates>[] buckets = WorldQueries.getTilesFromBuckets(world);

        // Update neighbors
        //WorldQueries.updateNeighbors(world);

        for (int b = BUCKETS - 1; b >= 0; b--) {
            for (Coordinates coords : buckets[b]) {
                applyFlowOnTile(world, context, coords.x(), coords.y());
            }
        }
    }

    private void applyFlowOnTile(World world, HydrologyContext context, int x, int y) {
        Tile tile = world.getTileWithWorldCoordinates(x, y);
        if (tile.getAltitude() <= world.getSeaLevel()) return;

        int index = context.getIndex(x, y);

        int neighborIndex = context.outNeighbor[index];
        if (neighborIndex == -1) return;

        TileWithCoordinates neighbor = context.getTileWithCoordinatesFromIndex(world, neighborIndex);
        float slope = WorldQueries.getSlope(x, y, tile, neighbor);

        float localFlow = context.water[index] * slope * strength;

        context.cumulativeFlow[index] += localFlow;
        context.flow[index] = localFlow;

        context.cumulativeFlow[neighborIndex] += context.cumulativeFlow[index];
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
