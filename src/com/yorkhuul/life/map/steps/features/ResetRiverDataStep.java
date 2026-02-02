package com.yorkhuul.life.map.steps.features;

import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyStep;
import com.yorkhuul.life.map.zone.world.World;

public class ResetRiverDataStep implements HydrologyStep {

    /**
     * This class should be used before running an hydric cycle
     */

    @Override
    public void apply(World world) {
        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            region.getTile(localX, localY).resetRiver();
        });
    }
}
