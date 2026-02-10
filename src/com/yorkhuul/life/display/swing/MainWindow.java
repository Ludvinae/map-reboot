package com.yorkhuul.life.display.swing;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel root;

    public MainWindow() {
        cardLayout = new CardLayout();
        root = new JPanel(cardLayout);

        setContentPane(root);
        setTitle("World Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }

    public void showScreen(String name) {
        cardLayout.show(root, name);
    }

    public void addScreen(String name, JPanel panel) {
        root.add(panel, name);
    }
}
