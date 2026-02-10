package com.yorkhuul.life.display.swing;

import javax.swing.*;
import java.awt.event.*;

public class PipelineWindow extends JDialog {
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
    private JLabel frquencyLabel;
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

    public PipelineWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
