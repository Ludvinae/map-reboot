package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.tools.Coordinates;

public class Line {

    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;

    public Line(Coordinates start, Coordinates end) {
        this.startX = start.x();
        this.startY = start.y();
        this.endX = end.x();
        this.endY = end.y();
    }

    public float sideOf(int x, int y) {
        int dX = endX - startX;
        int dY = endY - startY;

        int pX = x - startX;
        int pY = y - startY;

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
        float dx = endX - startX;
        float dy = endY - startY;

        float numerator = Math.abs(dx * (startY - y) - (startX - x) * dy);
        float denominator = (float) Math.sqrt(dx * dx + dy * dy);

        if (denominator == 0) {
            return 0f;
        }
        return numerator / denominator;
    }

}
