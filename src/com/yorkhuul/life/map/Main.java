package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.*;
import com.yorkhuul.life.map.generator.geology.Noise;
import com.yorkhuul.life.map.generator.geology.Tectonic;
import com.yorkhuul.life.map.generator.geology.TileVariance;
import com.yorkhuul.life.map.generator.geology.OceanBorders;
import com.yorkhuul.life.map.generator.hydrology.*;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int seed = "JavascriptCéPourLesNoobs".hashCode();
        //int seed = RandomSeed.getRandomSeed();
        World gaia = new World("gaia", 10, 10, seed);
        System.out.println(gaia);

        List<GenerationStep> geologySteps = List.of(
                new Noise(0.002f, 5, 0.95f),
                new OceanBorders(75, 0.95f),
                new Tectonic(30, "subduction", 0.01f, 10, 25, 100, 500, 0.35f),
                new Tectonic(50, "rift", 0.01f, 80, 150, 100, 500, 0.2f),
                new TileVariance(0.05f));
                //new Erosion(20, 0, 0.01f, 0.05f));

        List<HydrologyStep> hydrologySteps = List.of(
                new Rain(100, 50, 100, 0.45f),
                new WaterLevelOutflow(0.7f),
                new WaterFlow(1),
                new WaterErosion(0.35f, 0.05f));


        GenerationPipeline pipeline = new GenerationPipeline(gaia);
        gaia.setPipeline(pipeline);

        pipeline.runGeology(geologySteps);

        int hydrologyIterations = 50;
        for (int i = 0; i < hydrologyIterations; i++) {
            pipeline.runHydrology(hydrologySteps);
        }

        System.out.println("Percentage of land: " + gaia.percentImmerged() * 100 + " %");

        // Generation de l'image
        WorldRenderer render = new WorldRenderer(gaia, false);
        render.generateElevationImage(true);
        render.exportImage("_heightmap");
        /*
        render.generateElevationImage(false);
        render.exportImage("_elevation");
        render.generateWaterImage();
        render.exportImage("_water");

         */
        render.generateFlowImage();
        render.exportImage("_heathmap");

    }
}
