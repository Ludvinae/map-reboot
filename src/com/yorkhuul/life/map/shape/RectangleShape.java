package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.Distance;
import com.yorkhuul.life.map.zone.Region;

public class RectangleShape implements Shape{
    private Coordinates start;
    private int width;
    private int height;
    private float strength;

    public RectangleShape(Coordinates start, int width, int height, float strength) {
        this.start = start;
        this.width = width;
        this.height = height;
        this.strength = strength;
    }

    // Getters
    public float getStrength() {
        return strength;
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
    public float distanceFromCenter(Coordinates coords) {
        Coordinates center = getCenter();
        Distance dist = new Distance(coords, center);
        return dist.euclidianDistance();
    }

    public float influence(Coordinates coords) {
        float dist = distanceFromCenter(coords);

        // wip need to change formula for influence
        float influence = this.getStrength() - (1* dist);
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
