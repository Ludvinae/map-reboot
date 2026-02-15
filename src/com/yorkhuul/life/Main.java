package com.yorkhuul.life;

import com.yorkhuul.life.editor.ui.MainFrame;
import com.yorkhuul.life.editor.ui.old.OldMainFrame;
import com.yorkhuul.life.core.engine.context.EditorContext;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        EditorContext context = new EditorContext();

        SwingUtilities.invokeLater(() -> {
            MainFrame window = new MainFrame();

            /*
            window.addScreen("menu", new MenuPanel(window, context));
            window.addScreen("worldGen", new WorldGenPanel(window, context));
            window.addScreen("pipeline", new PipelinePanel(window, context));

             */

            window.showMenu();
            window.setVisible(true);
        });


        /*
        int seed = "JavascriptCéPourLesNoobs".hashCode();
        //int seed = RandomSeed.getRandomSeed();
        World gaia = new World("gaia", 10, 10, seed);
        System.out.println(gaia);

        List<GenerationStep> geologySteps = List.of(
                new Noise(0.002f, 5, 0.95f)
                ,new OceanBorders(75, 0.95f)
                ,new Tectonic(50, "subduction", 0.01f, 10, 25, 100, 500, 0.25f)
                ,new Tectonic(50, "rift", 0.01f, 80, 150, 100, 500, 0.1f)
                ,new Volcanic(10, 3, 10, 0.3f)
                ,new TileVariance(0.02f, 0.03f)
                //new Erosion(20, 0, 0.01f, 0.05f)
                );

        List<HydrologyStep> hydrologySteps = List.of(
                //new ResetRiverDataStep(),
                new Rain(100, 10, 35, 0.25f)
                ,new WaterFlow(0.9f)
                ,new WaterLevelOutflow(2, 0.6f, 0.005f)
                ,new WaterErosion(0.5f, 0.05f, 0.1f)
                //new FlowDecayStep(0.99f)
                );

        List<FeatureStep> featureSteps = List.of(new RiverStep());

        GenerationPipeline pipeline = new GenerationPipeline(gaia);
        gaia.setPipeline(pipeline);

        pipeline.runGeology(geologySteps, false);

        int hydrologyIterations = 25;
        for (int i = 0; i < hydrologyIterations; i++) {
            pipeline.runHydrology(hydrologySteps, false);
        }

        pipeline.runFeatures(featureSteps, false);

        System.out.println("Percentage of land: " + gaia.percentImmerged() * 100 + " %");

        // Generation de l'image
        WorldRenderer render = new WorldRenderer(gaia, false);
        render.generateElevationImage(true);
        render.exportImage("_heightmap");

        render.generateElevationImage(false);
        render.exportImage("_elevation");
        render.generateWaterImage();
        render.exportImage("_water");


        render.generateFlowImage();
        render.exportImage("_heatmap");

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
