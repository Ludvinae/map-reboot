package com.yorkhuul.life.core.engine.shape;

import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.core.world.region.Region;

public class MapEdges implements Shape {

    private final int worldWidth;
    private final int worldHeight;
    private final int coastWidth;
    private final float strength;

    public MapEdges(int worldWidth, int worldHeight,
                            int coastWidth, float strength) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.coastWidth = coastWidth;
        this.strength = strength;
    }

    @Override
    public float influence(Coordinates coords) {
        int dx = Math.min(coords.x(), worldWidth - 1 - coords.x());
        int dy = Math.min(coords.y(), worldHeight - 1 - coords.y());
        int dist = Math.min(dx, dy);

        if (dist >= coastWidth) {
            return 0f;
        }

        float t = 1f - (float) dist / coastWidth;
        return -strength * t;
    }

    @Override
    public boolean intersectsRegion(Region region) {
        // Cette shape touche toujours potentiellement les régions proches du bord
        return true;
    }


    public boolean contains(int worldX, int worldY) {
        if (worldX < coastWidth || worldWidth - worldX < coastWidth) return true;
        if (worldY < coastWidth || worldHeight - worldY < coastWidth) return true;
        return false;
    }
}
