package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.zone.Tile;

public interface Effect {

    void apply(Tile tile, float influence);
}
