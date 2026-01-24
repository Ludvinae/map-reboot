package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.*;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World gaia = new World("gaia", 10, 10);
        System.out.println(gaia);


        List<GenerationStep> steps = List.of(new Noise(0.5f),
                new Volcanic(50, 10, 50, 0.2f),
                new Tectonic(4, "subduction", 100, 500, 0.3f),
                new Tectonic(5, "rift", 100, 500, 0.1f),
                new OceanBorders(10, 0.7f));

        List<GenerationStep> test = List.of();

        WorldGenerator generator = new WorldGenerator(steps);
        generator.generate(gaia);
        //System.out.println(gaia.getRegion(5, 5));
        //System.out.println(gaia.getRegion(5, 5).getTile(5, 5));
        System.out.println(gaia.getRegion(1, 1));
        System.out.println(gaia.getRegion(1, 1).getTile(1, 1));


        // Generation de l'image
        WorldRenderer render = new WorldRenderer(gaia);
        render.generateElevationImage();
        render.exportImage("_elevation");

        /*
        WorldRenderer renderRelief = new WorldRenderer(gaia);
        renderRelief.generateReliefImage();
        renderRelief.exportImage("_relief");
         */
    }
}
