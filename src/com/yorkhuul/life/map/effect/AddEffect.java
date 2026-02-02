package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.zone.tile.Tile;

public class AddEffect implements Effect {

    @Override
    public void apply(Tile tile, float influence) {
        tile.addAltitude(influence);
    }
}
