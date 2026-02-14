package com.yorkhuul.life.core.viewdata;

import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.region.Region;
import com.yorkhuul.life.core.world.tile.Tile;

public class AltitudeMapView {
    Float[] altitudeMap;
    int width;
    int height;

    public AltitudeMapView(int width, int height) {
        this.altitudeMap = new  Float[width * height];
    }

    public Float[] getAltitudeMap() {
        return altitudeMap;
    }

    public void populateMap(World world) {
        int regionSize = Region.getSize();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Region region = world.getRegion(j, i);

                for (int y = 0; y < regionSize; y++) {
                    for (int x = 0; x < regionSize; x++) {

                        int worldX = j * regionSize + x;
                        int worldY = i * regionSize + y;

                        Tile tile = region.getTile(x, y);
                        altitudeMap[worldY * width + worldX] = tile.getAltitude();
                    }
                }
            }
        }
    }
}
