package com.yorkhuul.life.map.tools;

public record BoundingBox(int minX, int minY, int maxX, int maxY) {

    public Coordinates[] corners() {
        return new Coordinates[] {
                new Coordinates(minX, minY),
                new Coordinates(maxX, minY),
                new Coordinates(maxX, maxY),
                new Coordinates(minX, maxY)
        };
    }


}
