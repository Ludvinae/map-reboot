package com.yorkhuul.life.core.world;

import com.yorkhuul.life.core.engine.context.HydrologyContext;
import com.yorkhuul.life.core.world.tile.Tile;
import com.yorkhuul.life.utils.position.ArraytoMatrixIndex;

public final class WorldMutations {
    // Ecriture / effets sur le monde

    public static void transferWater(
            HydrologyContext context,
            int width,
            int fromX, int fromY,
            int toX, int toY,
            float amount
    ) {
        int indexFrom = ArraytoMatrixIndex.getIndex(fromX, fromY, width);
        int indexTo   = ArraytoMatrixIndex.getIndex(toX, toY, width);

        float[] waters = context.water;
        amount = Math.min(amount, waters[indexFrom]);
        if (amount <= 0) return;

        waters[indexFrom] -= amount;
        waters[indexTo] += amount;
    }

    public static void erode(World world, int wx, int wy, float amount) {
        Tile tile = world.getTileWithWorldCoordinates(wx, wy);
        amount = Math.min(amount, tile.getAltitude());
        addAltitude(tile, -amount);
        addAltitude(tile, amount);
    }

    public static void addAltitude(Tile tile, float amount) {
        tile.addAltitude(amount);
    }

}
