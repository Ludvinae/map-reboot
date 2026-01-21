package com.yorkhuul.life.map.terraforming;

public abstract class Shape {

    private int strength;
    private float falloff;

    public Shape(int strength, float falloff) {
        this.strength = strength;
        this.falloff = falloff;
    }

    public abstract void getEffect();
}
