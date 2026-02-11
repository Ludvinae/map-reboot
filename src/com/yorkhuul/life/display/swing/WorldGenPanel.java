package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.context.config.WorldConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldGenPanel extends JPanel implements Screen{
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

    private MainFrame window;
    private EditorContext context;

    public WorldGenPanel(MainFrame mainFrame, EditorContext context) {
        this.window = mainFrame;
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
        config.setFrequency(frequencySlider.getValue() / 1000f);
        config.setAmplitude(amplitudeSlider.getValue() / 100f);

        context.setWorldConfig(config);

        //context.setWorld(null); // reset
        //context.getStepConfigs().clear();

        window.showPipeline();

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
        window.showMenu();
    }

    @Override
    public void onDisplayed() {

    }
}
