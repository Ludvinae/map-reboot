package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.TileWithCoordinates;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class WaterErosion implements GenerationStep {

    private float strength;

    public WaterErosion(float strength) {
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        float[][] buffer = new float[world.getHeightInTiles()][world.getWidthInTiles()];

        List<TileWithCoordinates> tiles = world.getTilesContext();
        for (TileWithCoordinates tile: tiles) {
            float flow = tile.tile().getFlow();
            float slope = tile.slope();
            buffer[tile.worldY()][tile.worldX()] = -(flow * slope * strength);

        }
        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            tile.add(buffer[worldY][worldX]);
        });
    }
}
