package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.*;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //int seed = "javascriptcépourlesnoobs".hashCode();
        int seed = RandomSeed.getRandomSeed();
        World gaia = new World("gaia", 10, 10, seed);
        System.out.println(gaia);



        List<GenerationStep> steps = List.of(new Noise(0.003f, 5, 0.95f),
                    new OceanBorders(75, 0.95f),
                    new Tectonic(20, "subduction", 0.01f, 10, 25, 100, 500, 0.35f),
                    new Tectonic(50, "rift", 0.01f, 80, 150, 100, 500, 0.2f),
                    new TileVariance(0.05f),
                    new Erosion(20, 0, 0.01f, 0.05f),
                    new Rain(500, 5, 20, 0.2f),
                    new WaterFlow(1, 0f));


        List<GenerationStep> test = List.of(new Noise(0.003f, 4, 0.9f));

        WorldGenerator generator = new WorldGenerator(steps);
        generator.generate(gaia);
        System.out.println("Percentage of land: " + gaia.percentImmerged() * 100 + " %");

        // Generation de l'image
        WorldRenderer render = new WorldRenderer(gaia, false);
        render.generateElevationImage(true);
        render.exportImage("_elevation");


        gaia.applyReliefToRegions();
        WorldRenderer renderRelief = new WorldRenderer(gaia, true);
        renderRelief.generateReliefImage();
        renderRelief.exportImage("_relief");

        /*
        gaia.forEachTile((region, localX, localY, worldX, worldY) -> {
            System.out.println("Water: " + region.getTile(localX, localY).getWater() + " , Flow: " + region.getTile(localX, localY).getFlow());

        });

         */

    }
}
