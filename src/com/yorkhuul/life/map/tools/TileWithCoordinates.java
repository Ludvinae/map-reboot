package com.yorkhuul.life.map.tools;

import com.yorkhuul.life.map.zone.Tile;

public record TileWithCoordinates(
        Tile tile,
        int worldX,
        int worldY
) {

    public float getAltitude() {
        return tile.getAltitude();
    }

    public float getWater() {
        return tile.getWater();
    }

}
