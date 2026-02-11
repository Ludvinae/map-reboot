package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.context.WorldConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        context.getSteps().clear();

        window.showScreen("steps");
    }

    private void onBack() {
        // add your code here if necessary
        window.showScreen("menu");
    }

}
