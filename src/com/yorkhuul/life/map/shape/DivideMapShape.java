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
        int x = coords.x();
        int y = coords.y();

        float dist = line.distanceTo(x, y);
        if (dist > influenceRadius) return 0f;

        float side = line.sideOf(x, y); // -1 / +1
        float falloff = 1f - dist / influenceRadius;

        float factor = line.projectFactor(x, y);
        factor = Math.max(0f, (Math.min(1f, factor)));
        float centerDistance = Math.abs(factor - 0.5f) * 2f; // 0 au milieu, 1 aux extremités
        float longitudinalFallOff = 1f - (3f - 2f*centerDistance) * centerDistance * centerDistance;

        return switch (type) {
            case "rift" -> -falloff * strength * longitudinalFallOff;
            case "subduction" -> side * strength * falloff * longitudinalFallOff;
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
