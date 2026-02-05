package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.BoundingBox;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;
import com.yorkhuul.life.map.zone.world.WorldIterations;

public class ShapeEffect {

    private final Shape shape;
    private final EffectTarget target;
    private final float influence;

    public ShapeEffect(Shape shape, EffectTarget target, float influence) {
        this.shape = shape;
        this.target = target;
        this.influence = influence;
    }

    public void applyToRegion(World world, Region region) {
        BoundingBox boundaries = region.getWorldBounds();
        int startX = boundaries.minX();
        int endX = boundaries.maxX();
        int startY = boundaries.minY();
        int endY = boundaries.maxY();

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {

                if (!shape.contains(x, y)) continue;

                target.applyTile(world, x, y, influence);
            }
        }
    }

    public boolean intersectsRegion(Region region) {
        return shape.intersectsRegion(region);
    }

}
