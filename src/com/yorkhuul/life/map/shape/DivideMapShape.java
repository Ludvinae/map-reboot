package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.effect.Line;
import com.yorkhuul.life.map.tools.BoundingBox;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.zone.Region;
import libraries.FastNoiseLite;

import static libraries.FastNoiseLite.NoiseType.OpenSimplex2;

public class DivideMapShape implements Shape {

    private final Line line;
    private String type;
    private final int influenceRadius;
    private int seed;
    private float noiseScale;
    private final float strength;


    public DivideMapShape(Line line, String type, int influenceRadius, int seed, float noiseScale, float strength) {
        this.line = line;
        this.type = type;
        this.influenceRadius = influenceRadius;
        this.noiseScale = noiseScale;
        this.strength = strength;
    }

    public DivideMapShape(Line line, String type, int influenceRadius, int seed, float strength) {
        this(line, type, influenceRadius, seed, 3f, strength);
    }

    @Override
    public float influence(Coordinates coords) {
        int x = coords.x();
        int y = coords.y();

        float factor = line.projectFactor(x, y);
        factor = Math.max(0f, (Math.min(1f, factor)));

        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetNoiseType(OpenSimplex2);
        noise.SetFrequency(1f);
        float offset = noise.GetNoise(factor * noiseScale, 0);

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
        return false;
    }


}
