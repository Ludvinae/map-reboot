package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.steps.GenerationPipeline;
import com.yorkhuul.life.map.steps.features.FeatureStep;
import com.yorkhuul.life.map.steps.features.ResetRiverDataStep;
import com.yorkhuul.life.map.steps.features.RiverStep;
import com.yorkhuul.life.map.steps.generator.*;
import com.yorkhuul.life.map.steps.generator.geology.Noise;
import com.yorkhuul.life.map.steps.generator.geology.Tectonic;
import com.yorkhuul.life.map.steps.generator.geology.TileVariance;
import com.yorkhuul.life.map.steps.generator.geology.OceanBorders;
import com.yorkhuul.life.map.steps.generator.hydrology.*;
import com.yorkhuul.life.map.zone.world.World;

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
                new Tectonic(50, "subduction", 0.01f, 10, 25, 100, 500, 0.35f),
                new Tectonic(50, "rift", 0.01f, 80, 150, 100, 500, 0.2f),
                new TileVariance(0.05f));
                //new Erosion(20, 0, 0.01f, 0.05f));

        List<HydrologyStep> hydrologySteps = List.of(
                new ResetRiverDataStep(),
                new Rain(100, 50, 100, 0.25f),
                new WaterLevelOutflow(0.7f),
                new WaterFlow(0.7f),
                new WaterErosion(0.8f, 0.05f, 0.8f));
                //new FlowDecayStep(0.99f));

        List<FeatureStep> featureSteps = List.of(new RiverStep());

        GenerationPipeline pipeline = new GenerationPipeline(gaia);
        gaia.setPipeline(pipeline);

        pipeline.runGeology(geologySteps);

        int hydrologyIterations = 10;
        for (int i = 0; i < hydrologyIterations; i++) {
            pipeline.runHydrology(hydrologySteps);
        }

        pipeline.runFeatures(featureSteps);

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


        render.generateFlowImage();
        render.exportImage("_heathmap");
        */
        render.generateRiverImage();
        render.exportImage("_rivers");

        /*
        gaia.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            System.out.println(tile.getCumulativeFlow());
        });

         */
    }
}
