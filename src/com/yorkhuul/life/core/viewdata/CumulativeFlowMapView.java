package com.yorkhuul.life.core.viewdata;

import com.yorkhuul.life.core.world.World;

public class CumulativeFlowMapView implements MapView {

    public float[] generateMap(World world) {
        return world.getHydrologyContext().cumulativeFlow;
    }
}
