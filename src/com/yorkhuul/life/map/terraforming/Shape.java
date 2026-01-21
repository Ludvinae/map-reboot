package com.yorkhuul.life.map.terraforming;

public abstract class Shape {

    private float strength;
    private float falloff;

    public Shape(float strength, float falloff) {
        this.strength = strength;
        this.falloff = falloff;
    }

    protected float getStrength() {
        return this.strength;
    }

    protected float getFalloff() {
        return this.falloff;
    }

    public abstract void getEffect();
}
