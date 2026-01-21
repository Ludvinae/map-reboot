package com.yorkhuul.life.map.tools;

public class Distance {

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Distance(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double euclidianDistance() {
        int dX = this.x2 - this.x1;
        int dY = this.y2 - this.y1;

        return Math.sqrt((dX * dX) + (dY * dY));
    }

    public double manhattanDistance() {
        int dX = this.x1 - this.x2;
        int dY = this.y1 - this.y2;

        return Math.abs(dX) + Math.abs(dY);
    }
}
