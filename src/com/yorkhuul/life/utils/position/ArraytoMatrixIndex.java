package com.yorkhuul.life.utils.position;

public class ArraytoMatrixIndex {

    public static int getIndex(int worldX, int worldY, int width) {
        return worldY * width + worldX;
    }

    public static Coordinates getCoordinatesFromIndex(int index, int width) {
        int x = index % width;
        int y = index / width;
        return new Coordinates(x, y);
    }

}
