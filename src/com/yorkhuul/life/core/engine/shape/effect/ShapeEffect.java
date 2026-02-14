package com.yorkhuul.life.core.engine.shape.effect;

import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.world.WorldQueries;
import com.yorkhuul.life.utils.position.BoundingBox;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.core.world.region.Region;
import com.yorkhuul.life.core.world.World;

public class ShapeEffect {

    private final Shape shape;
    private final EffectTarget target;

    public ShapeEffect(Shape shape, EffectTarget target) {
        this.shape = shape;
        this.target = target;
    }

    public void applyToRegion(World world, Region region) {
        BoundingBox boundaries = region.getWorldBounds();
        int startX = boundaries.minX();
        int endX = boundaries.maxX();
        int startY = boundaries.minY();
        int endY = boundaries.maxY();
        int width = WorldQueries.getWorldWidth();

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {

                if (!shape.contains(x, y)) continue;

                float influence = shape.influence(new Coordinates(x, y));
                if (influence <= 0f) continue;

                target.applyTile(world, x, y, influence);
            }
        }
    }

    public boolean intersectsRegion(Region region) {
        return shape.intersectsRegion(region);
    }

}
