package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.Distance;
import com.yorkhuul.life.map.zone.Region;

public class CircleShape implements Shape{

    private Coordinates center;
    private int radius;
    private float strength;

    public CircleShape(Coordinates center, int radius, float strength) {
        this.center = center;
        setRadius(radius);
        setStrength(strength);
    }

    // Getters
    public Coordinates getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public float getStrength() {
        return strength;
    }

    // Setters
    public void setRadius(int radius) {
        if (radius < 1) {
            radius = 1;
        }
        this.radius = radius;
    }

    public void setStrength(float strength) {
        // strength limité entre 0 et 1, potentiellement à changer pour -1 - 1 si besoin
        if (strength > 1) {
            strength = 1;
        } else if (strength < 0) {
            strength = 0;
        }
        this.strength = strength;
    }

    // Methods
    public float distanceFromCenter(Coordinates coords) {
        Coordinates center = getCenter();
        Distance dist = new Distance(coords, center);
        return dist.euclidianDistance();
    }

    public float influence(Coordinates coords) {
        float dist = distanceFromCenter(coords);

        float influence = this.strength - ((this.getStrength() / radius) * dist);
        if (influence < 0) {
            influence = 0;
        }
        return influence;
    }

    @Override
    public boolean intersectsRegion(Region region) {
        return false;
    }


}
