package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.zone.Tile;

public class MultiplyEffect implements Effect {

    @Override
    public void apply(Tile tile, float influence) {
        tile.multiplyAltitude(influence);
    }
}
