package com.yorkhuul.life.map.effect;

import com.yorkhuul.life.map.zone.tile.Tile;

public interface Effect {

    void apply(Tile tile, float influence);
}
