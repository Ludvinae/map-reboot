package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.Distance;

public class CircleShape extends Shape {

    private Coordinates center;
    private int radius;

    public CircleShape(float strength, float falloff, Coordinates center, int radius) {
        super(strength, falloff);
        this.center = center;
        setRadius(radius);
    }

    // Getters
    public Coordinates getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void getEffect() {

    }

    // Setters
    public void setRadius(int radius) {
        if (radius < 1) {
            radius = 1;
        }
        this.radius = radius;
    }

    // Methods
    public double distanceFromCenter(Coordinates coords) {
        Coordinates center = getCenter();
        Distance dist = new Distance(coords, center);
        return dist.euclidianDistance();
    }

    public double influence(Coordinates coords) {
        double dist = distanceFromCenter(coords);

        double influence = this.getStrength() - (getFalloff() * dist);
        if (influence < 0) {
            influence = 0;
        }
        return influence;
    }


}
