package com.yorkhuul.life.display.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PipelinePanel extends JPanel {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel map;
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

    private MainWindow window;

    public PipelinePanel(MainWindow mainWindow) {
        this.window = mainWindow;
        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

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
        window.showScreen("steps");
    }

    private void onCancel() {
        // add your code here if necessary
        window.showScreen("worldGen");
    }


}
