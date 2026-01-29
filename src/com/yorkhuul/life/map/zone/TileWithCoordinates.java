package com.yorkhuul.life.map.zone;

public record TileWithCoordinates(
        Tile tile,
        int worldX,
        int worldY,

        TileWithCoordinates lowestNeighbor,
        float slope
) {

    public float getAltitude() {
        return tile.getAltitude();
    }

    public float getWater() {
        return tile.getWater();
    }

}
