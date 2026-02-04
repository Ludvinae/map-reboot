package com.yorkhuul.life.map.effect.hydrology;

import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;

public class ShapeHydrologyEffect {

    private final Shape shape;
    private final HydrologyEffect effect;

    public ShapeHydrologyEffect(Shape shape,
                                HydrologyEffect effect) {
        this.shape = shape;
        this.effect = effect;
    }

    public void apply(World world) {
        //HydrologyContext context = world.getHydrologyContext();

        WorldIterations.forEachTile(world, (x, y, tile) -> {
            //if (!shape.contains(x, y)) return;

            float influence = shape.influence(new Coordinates(x, y));
            effect.apply(world, x, y, influence);
        });
    }

    public boolean intersectsRegion(Region region) {
        return shape.intersectsRegion(region);
    }
}
