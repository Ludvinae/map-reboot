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

    @Override
    public float influence(Coordinates coords) {
        int dx = coords.x() - center.x();
        int dy = coords.y() - center.y();
        float distanceSquared = dx * dx + dy * dy;

        if (distanceSquared > radius * radius) {
            return 0f;
        }

        float t = 1f - (float)Math.sqrt(distanceSquared) / radius;
        return t * strength;
    }


    @Override
    public boolean intersectsRegion(Region region) {
        Coordinates minCoords = region.getWorldCoordinates(0, 0);
        int size = Region.getSize();
        int x = minCoords.x() + size;
        int y = minCoords.y() + size;
        Coordinates maxCoords = new Coordinates(x, y);


        int closestX = Math.max(minCoords.x(), Math.min(center.x(), maxCoords.x()));
        int closestY = Math.max(minCoords.y(), Math.min(center.y(), maxCoords.y()));


        int dx = center.x() - closestX;
        int dy = center.y() - closestY;

        return (dx * dx + dy * dy) <= radius * radius;
    }



}
