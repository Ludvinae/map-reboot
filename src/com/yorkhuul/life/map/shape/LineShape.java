package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.Region;

public class LineShape implements Shape{

    private Coordinates start;
    private Coordinates end;
    private float strength;

    public LineShape(Coordinates start, Coordinates end, float strength) {
        this.start = start;
        this.end = end;
        this.strength = strength;
    }

    public LineShape(Coordinates start, Coordinates end) {
        this(start, end, 0);
    }


    @Override
    public float influence(Coordinates coords) {
        float distance = distanceTo(coords.x(), coords.y());

        float t = 1f - distance;
        return t * strength;
    }


    @Override
    public boolean intersectsRegion(Region region) {
        return false;
    }

    public float sideOf(int x, int y) {
        int dX = line().x();
        int dY = line().y();

        int pX = x - start.x();
        int pY = y - start.y();

        int cross = dX * pY - dY * pX;

        if (cross > 0) {
            return 1f;
        }
        if (cross < 0) {
            return -1f;
        }
        return 0f;
    }

    public float distanceTo(int x, int y) {
        float dx = line().x();
        float dy = line().y();

        float numerator = Math.abs(dx * (start.y() - y) - (start.x() - x) * dy);
        float denominator = (float) Math.sqrt(dx * dx + dy * dy);

        return numerator / denominator;
    }

    public Coordinates line() {
        int dx = end.x() - start.x();
        int dy = end.y() - start.y();

        return new Coordinates(dx, dy);
    }

}
