package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.context.config.WorldConfig;
import com.yorkhuul.life.map.steps.generator.geology.Noise;
import com.yorkhuul.life.map.zone.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PipelinePanel extends JPanel implements Screen{
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

    private MainFrame window;
    private EditorContext context;

    public PipelinePanel(MainFrame mainFrame, EditorContext context) {
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

    @Override
    public void onDisplayed() {

        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {

            @Override
            protected BufferedImage doInBackground() {

                generateBaseWorld();
                WorldRenderer renderer = new WorldRenderer(context.getWorld(), false);
                renderer.generateElevationImage(true);
                return renderer.getImage();

            }

            @Override
            protected void done() {
                try {
                    BufferedImage image = get();
                    mapDisplayPanel.setImage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }


    public void generateBaseWorld() {

        WorldConfig config = context.getWorldConfig();

        World world = new World(config.getName(), config.getWidth(), config.getHeight(), config.getSeed().hashCode());

        Noise noiseStep = new Noise(config.getFrequency(), config.getAmplitude());
        noiseStep.apply(world);

        context.setWorld(world);

        updatePreview();
    }

    private void updatePreview() {

        World world = context.getWorld();

        WorldRenderer renderer = new WorldRenderer(world, false);
        renderer.generateElevationImage(true);
        BufferedImage image = renderer.getImage();

        mapDisplayPanel.setImage(image);
    }



}
