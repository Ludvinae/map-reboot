package com.yorkhuul.life.core.engine.steps.generator.wip;

import com.yorkhuul.life.core.engine.context.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.world.region.Region;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class SmoothRegions {

    private float strength;
    List<Parameter<?>> parameters = new ArrayList<>();

    public SmoothRegions(float strength) {
        setStrength(strength);
    }

    public void setStrength(float strength) {
        this.strength = Math.max(0f, Math.min(1f, strength));
    }

    public void apply(World world, EditorContext context) {
        for (int ry = 0; ry < world.getHeight(); ry++) {
            for (int rx = 0; rx < world.getWidth(); rx++) {
                Region region = world.getRegion(rx, ry);
                region.normalize(strength);
            }
        }

    }

}
