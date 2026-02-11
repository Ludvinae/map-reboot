package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.context.EditorContext;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private EditorContext context;
    private CardLayout cardLayout = new CardLayout();;
    private JPanel root = new JPanel(cardLayout);

    public MainWindow(EditorContext context) {
        this.context = context;

        setContentPane(root);
        setTitle("World Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);
    }

    public EditorContext getContext() {
        return context;
    }

    public void showScreen(String name) {
        cardLayout.show(root, name);
    }

    public void addScreen(String name, JPanel panel) {
        root.add(panel, name);
    }
}
