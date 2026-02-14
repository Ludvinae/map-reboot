package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.config.WorldConfig;
import com.yorkhuul.life.map.config.geology.NoiseConfig;
import com.yorkhuul.life.map.context.EditorContext;

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
    private JLabel widthValueLabel;
    private JLabel heightValueLabel;
    private JLabel amplitudeValueLabel;
    private JLabel frequencyValueLabel;

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

        slidersLabelListeners();
    }

    private void onGenerate() {
        WorldConfig config = new WorldConfig();
        config.setName(nameField.getText());
        config.setSeed(seedField.getText());
        config.setWidth(widthSlider.getValue());
        config.setHeight(heightSlider.getValue());
        config.setFrequency(getFrequency());
        config.setAmplitude(getAmplitude());

        NoiseConfig noise = new NoiseConfig();
        noise.setFrequency(frequencySlider.getValue());
        noise.setStrength(amplitudeSlider.getValue());

        context.setWorldConfig(config);
        // Need to cleanup the mess eventually
        context.setNoiseConfig(noise);
        context.addToConfigList(noise);

        window.showPipeline();
        window.revalidate();
        window.repaint();
    }

    private void onBack() {
        window.showMenu();
    }

    @Override
    public void onDisplayed() {
    }

    private float getAmplitude() {
        return amplitudeSlider.getValue() /  100f;
    }

    private float getFrequency() {
        return frequencySlider.getValue() /  10000f;
    }

    private void slidersLabelListeners() {
        widthSlider.addChangeListener(e -> {
            int value = widthSlider.getValue();
            widthValueLabel.setText(String.format("%d", value));
        });

        heightSlider.addChangeListener(e -> {
            int value = heightSlider.getValue();
            heightValueLabel.setText(String.format("%d", value));
        });

        frequencySlider.addChangeListener(e -> {
            float value = getFrequency();
            frequencyValueLabel.setText(String.format("%.5f", value));
        });

        amplitudeSlider.addChangeListener(e -> {
            float value = getAmplitude();
            amplitudeValueLabel.setText(String.format("%.2f", value));
        });
    }

}
