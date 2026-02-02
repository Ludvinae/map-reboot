package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.Tile;

public class ShapeEffect {

    private final Shape shape;
    private final Effect effect;

    public ShapeEffect(Shape shape, Effect effect) {
        this.shape = shape;
        this.effect = effect;
    }

    public void applyTo(Tile tile, Coordinates coords) {
        float influence = shape.influence(coords);
        if (influence != 0f) {
            effect.apply(tile, influence);
        }
    }

    public boolean intersectsRegion(Region region) {
        return shape.intersectsRegion(region);
    }

}
