package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.Distance;

public class RectangleShape{
    private Coordinates start;
    private int width;
    private int height;

    public RectangleShape(float strength, float falloff, Coordinates start, int width, int height) {
        super(strength, falloff);
        this.start = start;
        this.width = width;
        this.height = height;
    }

    // Getters
    // wip
    @Override
    public void getEffect() {

    }

    public Coordinates getEnd() {
        return new Coordinates(start.x() + this.width, start.y() + this.height);
    }

    public Coordinates getCenter() {
        Coordinates end = getEnd();
        int x = Math.floorDiv (end.x() - start.x(), 2);
        int y = Math.floorDiv (end.y() - start.y(), 2);
        return new Coordinates(x, y);
    }

    // Setters

    // Others

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
