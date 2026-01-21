package com.yorkhuul.life.map.terraforming;

public abstract class Shape {

    private float strength;
    private float falloff;

    public Shape(float strength, float falloff) {
        this.strength = strength;
        this.falloff = falloff;
    }

    public abstract void getEffect();
}
