package com.yorkhuul.life.core.engine.shape;

import com.yorkhuul.life.core.engine.pipeline.geology.TectonicType;
import com.yorkhuul.life.core.engine.shape.effect.Line;
import com.yorkhuul.life.utils.position.BoundingBox;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.region.Region;

public class DivideMapShape implements Shape {

    private final Line line;
    private TectonicType type;
    private final int influenceRadius;
    private NoiseService noise;
    private float frequency;
    private final float strength;


    public DivideMapShape(Line line, TectonicType type, int influenceRadius, NoiseService noise, float frequency, float strength) {
        this.line = line;
        this.type = type;
        this.influenceRadius = influenceRadius;
        this.noise = noise;
        this.frequency = frequency;
        this.strength = strength;
    }

    public DivideMapShape(Line line, TectonicType type, int influenceRadius, NoiseService noise, float strength) {
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

        if (type == TectonicType.RIFT) return -lateralFalloff * longitudinalFallOff * strength;
        else if (type == TectonicType.SUBDUCTION) return lateralFalloff * longitudinalFallOff * strength;
        else throw new RuntimeException("Invalid Tectonic Type");

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


    @Override
    public boolean contains(int x, int y) {
        float distance = line.distanceTo(x, y);
        return distance <= influenceRadius;
    }


}
