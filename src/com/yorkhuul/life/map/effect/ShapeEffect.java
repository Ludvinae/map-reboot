package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.shape.Shape;
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

    public void apply(World world) {
        WorldIterations.forEachTile(world, (worldX, worldY, tile) -> {
            if (!shape.contains(worldX, worldY)) return;
            target.applyTile(world, worldX, worldY, influence);
        });
    }

    public boolean intersectsRegion(Region region) {
        return shape.intersectsRegion(region);
    }

}
