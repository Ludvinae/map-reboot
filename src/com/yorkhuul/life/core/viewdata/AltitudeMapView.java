package com.yorkhuul.life.core.viewdata;

import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.core.world.region.Region;
import com.yorkhuul.life.core.world.tile.Tile;

import java.util.Arrays;

public class AltitudeMapView implements MapView {

    public float[] generateMap(World world) {
        int width = world.getWidth();
        int height = world.getHeight();
        float[] altitudeMap = new float[world.getWidthInTiles() * world.getHeightInTiles()];

        int regionSize = Region.getSize();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Region region = world.getRegion(j, i);

                for (int y = 0; y < regionSize; y++) {
                    for (int x = 0; x < regionSize; x++) {

                        int worldX = j * regionSize + x;
                        int worldY = i * regionSize + y;

                        Tile tile = region.getTile(x, y);
                        //System.out.println(tile.getAltitude());
                        altitudeMap[worldY * world.getWidthInTiles() + worldX] = tile.getAltitude();
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(altitudeMap));
        return altitudeMap;
    }
}
