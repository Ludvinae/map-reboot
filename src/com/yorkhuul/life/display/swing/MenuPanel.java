package com.yorkhuul.life.display.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private JPanel contentPane;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JButton newButton;
    private JButton loadButton;

    private MainWindow window;

    public MenuPanel(MainWindow mainWindow) {
        this.window = mainWindow;

        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNew();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLoad();
            }
        });
    }

    private void onNew() {
        // add your code here
        window.showScreen("worldGen");
    }

    private void onLoad() {
        // add your code here if necessary
        window.showScreen("menu");
    }

}
