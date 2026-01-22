package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.effect.Line;
import com.yorkhuul.life.map.tools.BoundingBox;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.Region;

public class DivideMapShape implements Shape {

    private final Line line;
    private final int influenceRadius;
    private final float strength;
    private String type;

    public DivideMapShape(Line line, String type, int influenceRadius, float strength) {
        this.line = line;
        this.influenceRadius = influenceRadius;
        this.strength = strength;
        this.type = type;
    }

    @Override
    public float influence(Coordinates coords) {
        float dist = line.distanceTo(coords.x(), coords.y());
        if (dist > influenceRadius) return 0f;

        float side = line.sideOf(coords.x(), coords.y()); // -1 / +1
        float falloff = 1f - dist / influenceRadius;

        return switch (type) {
            case "rift" -> -falloff * strength;
            case "subduction" -> side * strength * falloff;
            case null, default -> 0f;
        };

    }

    @Override
    public boolean intersectsRegion(Region region) {
        BoundingBox box = region.getWorldBounds();

        for (Coordinates corner: box.corners()) {
            if (line.distanceTo(corner.x(), corner.y()) <= influenceRadius) {
                return true;
            }
        }
        return false;
    }


}
