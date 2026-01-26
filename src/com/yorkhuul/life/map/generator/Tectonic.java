package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.effect.AddEffect;
import com.yorkhuul.life.map.effect.Effect;
import com.yorkhuul.life.map.effect.Line;
import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.shape.DivideMapShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.tools.RandomSpot;
import com.yorkhuul.life.map.zone.World;

import java.util.Objects;

public class Tectonic implements GenerationStep {

    private int count;
    private String type;
    private int seed;
    private int influenceMin;
    private int influenceMax;
    private float strength;

    public Tectonic(int count, String type, int seed, int minRadius, int maxRadius, float strength) {
        setCount(count);
        setType(type);
        this.seed = seed;
        this.influenceMin = minRadius;
        this.influenceMax = maxRadius;
        this.strength = strength;
    }

    public Tectonic(int count, String type, int minRadius, int maxRadius, float strength) {
        this(count, type, RandomSeed.getRandomSeed(), minRadius, maxRadius, strength);
    }


    // Setters
    public void setCount(int count) {
        if (count <= 1) {
            count = 1;
        }
        this.count = count;
    }

    public void setType(String type) {
        if (!Objects.equals(type, "rift") && !Objects.equals(type, "subduction")) {
            type = "subduction";
        }
        this.type = type;
    }

    public void setInfluenceMax(int influenceMax) {
        if (influenceMax <= this.influenceMin) {
            influenceMax = this.influenceMin + 1;
        }
        this.influenceMax = influenceMax;
    }

    //Methods
    @Override
    public void apply(World world) {
        for (int i = 0; i < this.count; i++) {
            Coordinates coordsStart = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();
            Coordinates coordsEnd = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();
            while (coordsStart == coordsEnd) {
                coordsEnd = new RandomSpot(world.getWidthInTiles(), world.getHeightInTiles()).getCoords();
            }

            int radius = new RandomInteger(this.influenceMin, this.influenceMax).getRandomInt();
            float influence = (float) (strength * Math.random());

            Line line = new Line(coordsStart, coordsEnd);
            Effect effect = new AddEffect();
            Shape divideMap = new DivideMapShape(line, type, radius, seed, influence);

            ShapeEffect tectonic = new ShapeEffect(divideMap, effect);

            world.applyShapeEffect(tectonic);
        }
        consoleFeedback("Tectonic " + type + " x " + count);
    }
}
