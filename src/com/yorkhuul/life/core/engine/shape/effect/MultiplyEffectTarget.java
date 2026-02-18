package com.yorkhuul.life.core.engine.shape.effect;

import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.core.world.World;

public class MultiplyEffectTarget implements EffectTarget {

    @Override
    public void applyTile(World world, int worldX, int worldY, float influence) {
        Tile tile = world.getTileWithWorldCoordinates(worldX, worldY);
        tile.multiplyAltitude(influence);
    }
}
