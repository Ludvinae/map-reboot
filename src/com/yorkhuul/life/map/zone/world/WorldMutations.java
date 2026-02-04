package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.zone.tile.Tile;

public final class WorldMutations {
    // Ecriture / effets sur le monde

    public static void transferWater(
            HydrologyContext context,
            int fromX, int fromY,
            int toX, int toY,
            float amount
    ) {
        int indexFrom = context.getIndex(fromX, fromY);
        int indexTo   = context.getIndex(toX, toY);

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

    public static void addSediment(Tile tile, float amount) {
        tile.addSediment(amount);
    }

    public static void setSediment(Tile tile, float amount) {
        tile.setSediment(amount);
    }

    public static void addWater(float[] water, int index, float amount) {
        water[index] += amount;
    }
}
