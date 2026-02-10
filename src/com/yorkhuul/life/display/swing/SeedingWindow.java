package com.yorkhuul.life.display.swing;

import javax.swing.*;
import java.awt.event.*;

public class SeedingWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
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

    public SeedingWindow() {
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

    public static void main(String[] args) {
        SeedingWindow dialog = new SeedingWindow();
        dialog.pack();
        dialog.setVisible(true);
    }
}
