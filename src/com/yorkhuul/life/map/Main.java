package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.*;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World gaia = new World("gaia", 10, 10);
        System.out.println(gaia);


        List<GenerationStep> steps = List.of(new Noise(356537763, 0.003f, 5, 1.7f, 0.95f),
                    new OceanBorders(75, 0.6f),
                    new Tectonic(5, "subduction", 30, 50, 0.05f),
                    //new Tectonic(5, "rift", 30, 50, 0.1f),
                    new Erosion(10, 0, 0.01f, 0.5f));


        List<GenerationStep> test = List.of(new Noise(12345679, 0.003f, 4, 1.7f, 0.9f));

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
