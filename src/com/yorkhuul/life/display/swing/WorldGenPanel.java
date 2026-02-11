package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.context.config.WorldConfig;
import com.yorkhuul.life.map.zone.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Map;

public class WorldGenPanel extends JPanel {
    private JPanel contentPane;
    private JButton buttonGenerate;
    private JButton buttonBack;
    private JTextField seedField;
    private JTextField nameField;
    private JPanel seedPanel;
    private JPanel namePanel;
    private JSlider widthSlider;
    private JSlider heightSlider;
    private JPanel sizePanel;
    private JPanel noisePanel;
    private JSlider frequencySlider;
    private JSlider amplitudeSlider;
    private JLabel amplitudeLabel;
    private JLabel frequencyLabel;

    private MainWindow window;
    private EditorContext context;

    public WorldGenPanel(MainWindow mainWindow, EditorContext context) {
        this.window = mainWindow;
        this.context = context;

        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        buttonGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onGenerate();
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });
    }

    private void onGenerate() {
        WorldConfig config = new WorldConfig();
        config.setName(nameField.getText());
        config.setSeed(seedField.getText());
        config.setWidth(widthSlider.getValue());
        config.setHeight(heightSlider.getValue());

        context.setWorldConfig(config);

        context.setWorld(null); // reset
        context.getStepConfigs().clear();

        window.showScreen("pipeline");

        /*
        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {

            @Override
            protected BufferedImage doInBackground() {
                World world = engine.generate(context);
                return renderer.render(world);
            }

            @Override
            protected void done() {
                try {
                    BufferedImage image = get();
                    MapDisplayPanel.setImage(image);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        worker.execute();

         */
    }

    private void onBack() {
        // add your code here if necessary
        window.showScreen("menu");
    }

}
