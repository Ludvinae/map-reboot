package com.yorkhuul.life.core.engine.steps.generator.hydrology;

import com.yorkhuul.life.core.engine.config.hydrology.FlowConfig;
import com.yorkhuul.life.core.engine.context.HydrologyContext;
import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.utils.position.ArraytoMatrixIndex;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.tile.TileWithCoordinates;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.WorldQueries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yorkhuul.life.core.world.WorldQueries.BUCKETS;

public class WaterFlow implements HydrologyStep<FlowConfig> {

    @Override
    public void apply(World world, FlowConfig config) {
        HydrologyContext context = world.getHydrologyContext();
        if (context == null) System.out.println("No pipeline associated with this world");

        Arrays.fill(context.flow, 0f);
        Arrays.fill(context.cumulativeFlow, 0f);
        List<Coordinates>[] buckets = WorldQueries.getTilesFromBuckets(world);

        // Update neighbors
        //WorldQueries.updateNeighbors(world);

        for (int b = BUCKETS - 1; b >= 0; b--) {
            for (Coordinates coords : buckets[b]) {
                applyFlowOnTile(world, context, coords.x(), coords.y(), config);
            }
        }
    }

    private void applyFlowOnTile(World world, HydrologyContext context, int x, int y, FlowConfig config) {
        Tile tile = world.getTileWithWorldCoordinates(x, y);
        if (tile.getAltitude() <= world.getSeaLevel()) return;

        int index = ArraytoMatrixIndex.getIndex(x, y, WorldQueries.getWorldWidth());

        int neighborIndex = context.outNeighbor[index];
        if (neighborIndex == -1) return;

        TileWithCoordinates neighbor = context.getTileWithCoordinatesFromIndex(world, neighborIndex);
        float slope = WorldQueries.getSlope(x, y, tile, neighbor);

        float localFlow = context.water[index] * slope * config.getStrength();

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


    @Override
    public String getName() {
        return "Flow";
    }

    @Override
    public List<Parameter<?>> createParameters(FlowConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, config.getStrength(), 0.01f, config::setStrength));

        return parameters;
    }

}
