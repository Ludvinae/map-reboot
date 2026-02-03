package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.zone.tile.Tile;

public final class WorldMutations {
    // Ecriture / effets sur le monde

    public static void transferWater(
            World world,
            int fromX, int fromY,
            int toX, int toY,
            float amount
    ) {
        Tile from = world.getTileWithWorldCoordinates(fromX, fromY);
        Tile to   = world.getTileWithWorldCoordinates(toX, toY);

        amount = Math.min(amount, from.getWater());
        if (amount <= 0) return;

        from.addWater(-amount);
        to.addWater(amount);
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
