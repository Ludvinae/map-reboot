package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.effect.Line;
import com.yorkhuul.life.map.tools.BoundingBox;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.region.Region;

public class DivideMapShape implements Shape {

    private final Line line;
    private String type;
    private final int influenceRadius;
    private NoiseService noise;
    private float frequency;
    private final float strength;


    public DivideMapShape(Line line, String type, int influenceRadius, NoiseService noise, float frequency, float strength) {
        this.line = line;
        this.type = type;
        this.influenceRadius = influenceRadius;
        this.noise = noise;
        this.frequency = frequency;
        this.strength = strength;
    }

    public DivideMapShape(Line line, String type, int influenceRadius, NoiseService noise, float strength) {
        this(line, type, influenceRadius, noise, 3f, strength);
    }

    @Override
    public float influence(Coordinates coords) {
        int x = coords.x();
        int y = coords.y();

        float factor = line.projectFactor(x, y);
        factor = Math.max(0f, (Math.min(1f, factor)));

        float offset = noise.sample(factor * 3f, 0, frequency);

        float amplitude = influenceRadius * 0.3f;
        float dist = line.distanceTo(x, y) + offset * amplitude;

        if (dist > influenceRadius) return 0f;

        //float side = line.sideOf(x, y); // -1 / +1

        float lateralFalloff = 1f - dist / influenceRadius;
        float longitudinalFallOff = 1f - Math.abs(factor - 0.5f) * 2f;

        return switch (type) {
            case "rift" -> -lateralFalloff * longitudinalFallOff * strength;
            //case "subduction" -> side * lateralFalloff * longitudinalFallOff;
            case "subduction" -> lateralFalloff * longitudinalFallOff * strength;
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
        return false;}


    public boolean contains(int worldX, int worldY) {
        return false;
    }

}
