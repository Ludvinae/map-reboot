package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSpot;
import com.yorkhuul.life.map.zone.Region;

import java.util.Random;

public class LineShape implements Shape{

    private Coordinates start;
    private int radius;
    private int thickness;
    private float strength;
    private Coordinates end;

    public LineShape(Coordinates start, int radius, int thickness, float strength) {
        this.start = start;
        this.radius = radius;
        this.thickness = thickness;
        this.strength = strength;
        setEnd();
    }

    public int getThickness() {
        return thickness;
    }

    public float getStrength() {
        return strength;
    }

    private void setEnd() {
        RandomInteger randX = new RandomInteger(start.x() - radius, start.x() + radius);
        RandomInteger randY = new RandomInteger(start.y() - radius, start.y() + radius);

        this.end = new Coordinates(randX.getRandomInt(), randY.getRandomInt());
    }

    @Override
    public float influence(Coordinates coords) {
        return 0;
    }

    @Override
    public boolean intersectsRegion(Region region) {
        return false;
    }
}
