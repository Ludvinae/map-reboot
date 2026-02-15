package com.yorkhuul.life.editor.ui.old;

import com.yorkhuul.life.core.engine.pipeline.OldGenerationPipeline;
import com.yorkhuul.life.editor.render.WorldRenderer;
import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.editor.ui.EditorContext;
import com.yorkhuul.life.core.engine.pipeline.foundation.Noise;
import com.yorkhuul.life.core.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PipelinePanel extends JPanel implements Screen {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pipeline;
    private JComboBox generatorComboBox;
    private JPanel stepPanel;
    private JComboBox stepComboBox;
    private JLabel stepTitleLabel;
    private JPanel settingsPanel;
    private JPanel stepTitlePanel;
    private JSlider countSlider;
    private JLabel countLabel;
    private JLabel typeLabel;
    private JComboBox typeComboBox;
    private JLabel frequencyLabel;
    private JSlider frequencySlider;
    private JSlider distanceMaxSlider;
    private JSlider strengthSlider;
    private JLabel minRadiusLabel;
    private JSlider minRadiusSlider;
    private JLabel maxRadiusLabel;
    private JSlider maxRadiusSlider;
    private JLabel distanceMinLabel;
    private JSlider distanceMinSlider;
    private JLabel distanceMaxLabel;
    private JLabel strengthLabel;
    private JPanel controlPanel;
    private JPanel mainPanel;

    private JPanel mapPanel;
    private MapDisplayPanel mapDisplayPanel;

    private OldMainFrame window;
    private EditorContext context;

    public PipelinePanel(OldMainFrame mainFrame, EditorContext context) {
        this.window = mainFrame;
        this.context = context;

        mapDisplayPanel = new MapDisplayPanel();
        mapPanel.setLayout(new BorderLayout());
        mapPanel.add(mapDisplayPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        System.out.println("Designer instance: " + mapPanel);
        System.out.println("Designer instance: " + mapDisplayPanel);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        // add your code here
        window.showPipeline();
    }

    private void onCancel() {
        // add your code here if necessary
        window.showWorldGen();
    }

    public void onDisplayed() {

        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {

            @Override
            protected BufferedImage doInBackground() {

                generateBaseWorld(); // modifie le world uniquement
                World world = context.getWorld();

                WorldRenderer renderer = new WorldRenderer(world.getWidthInTiles(), world.getHeightInTiles());
                renderer.generateAltitudeImage(world);

                return renderer.getImage();
            }

            @Override
            protected void done() {
                try {
                    BufferedImage image = get();
                    mapDisplayPanel.setImage(image); // UI ici uniquement
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }


    public void generateBaseWorld() {

        WorldConfig config = context.getWorldConfig();
        World world = new World(config);
        context.setWorld(world);

        Noise noiseStep = new Noise();

        OldGenerationPipeline pipeline = new OldGenerationPipeline(context);
        pipeline.runNoise(noiseStep, false);

    }

    private void updatePreview() {

        World world = context.getWorld();
        //testWorldAltitude(world);

        WorldRenderer renderer = new WorldRenderer(world.getWidthInTiles(), world.getHeightInTiles());
        renderer.generateAltitudeImage(world);
        BufferedImage image = renderer.getImage();

        mapDisplayPanel.setImage(image);
    }

    private void testWorldAltitude(World world) {
        for (int y = 0; y < world.getHeightInTiles(); y++) {
            for (int x = 0; x < world.getWidthInTiles(); x++) {
                System.out.println(world.getTileWithWorldCoordinates(x, y).getAltitude());
            }
        }
    }

}
