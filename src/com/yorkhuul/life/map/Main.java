package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.*;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World gaia = new World("gaia", 10, 10);
        System.out.println(gaia);


        List<GenerationStep> steps = List.of(new Noise(0.3f),
                new SmoothRegions(0.4f),
                new Volcanic(100, 1, 10, 0.2f),
                new Tectonic(4, "subduction", 100, 500, 0.3f),
                new Tectonic(5, "rift", 100, 500, 0.1f),
                new OceanBorders(100, 0.7f),
                new SmoothRegions(0.1f));

        List<GenerationStep> test = List.of(new Noise(0.3f),
                new SmoothRegions(0.6f),
                new Tectonic(5, "rift", 100, 500, 0.2f),
                new Tectonic(4, "subduction", 100, 500, 0.3f),
                new Volcanic(100, 1, 10, 0.2f),
                new SmoothRegions(0.1f));

        WorldGenerator generator = new WorldGenerator(steps);
        generator.generate(gaia);

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
