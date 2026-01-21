package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.World;

public class Volcanic implements GenerationStep{

    private int count;
    private int minRadius;
    private int maxRadius;
    private float strength;

    public Volcanic(int count, int minRadius, int maxRadius, float strength) {
        this.count = count;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.strength = strength;
    }

    public Volcanic() {
        this(100, 5, 10, 0.5f);
    }

    @Override
    public void apply(World world) {
        for (int i = 0; i < this.count; i++) {


        }
    }
}
