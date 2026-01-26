package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.*;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World gaia = new World("gaia", 10, 10);
        System.out.println(gaia);
        //int seed = 1546848646;
        int seed = RandomSeed.getRandomSeed();


        List<GenerationStep> steps = List.of(new Noise(seed, 0.003f, 5, 1.7f, 0.95f),
                    new OceanBorders(75, 0.6f),
                    new Tectonic(20, "subduction", seed, 10, 25, 0.35f),
                    new Tectonic(50, "rift", seed, 80, 150, 0.15f),
                    new Erosion(1, 0, 0.01f, 0.5f));


        List<GenerationStep> test = List.of(new Noise(seed, 0.003f, 4, 1.7f, 0.9f));

        WorldGenerator generator = new WorldGenerator(steps);
        generator.generate(gaia);
        System.out.println("Percentage of land: " + gaia.percentImmerged() * 100 + " %");

        // Generation de l'image
        WorldRenderer render = new WorldRenderer(gaia);
        render.generateElevationImage();
        render.exportImage("_elevation");

        /*
        gaia.applyReliefToRegions();
        WorldRenderer renderRelief = new WorldRenderer(gaia);
        renderRelief.generateReliefImage();
        renderRelief.exportImage("_relief");
         */
    }
}
