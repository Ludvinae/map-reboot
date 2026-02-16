package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout layout;
    private final JPanel mainPanel;

    private MenuPanel menuPanel;
    private EditorPanel editorPanel;
    private String MAIN_MENU = "MENU";
    private String EDITOR = "EDITOR";

    private EditorContext context;

    public MainFrame() {
        this.layout = new CardLayout();
        this.mainPanel = new JPanel(layout);

        context = new EditorContext();
        this.menuPanel = new MenuPanel(this);
        this.editorPanel = new EditorPanel(context);

        mainPanel.add(MAIN_MENU, menuPanel);
        mainPanel.add(EDITOR, editorPanel);

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);

    }

    public void startApp() {
        showMenu();
        setVisible(true);
    }


    private void showMenu() {
        layout.show(mainPanel, MAIN_MENU);
    }

    private void showEditor() {
        layout.show(mainPanel, EDITOR);
    }

    public void startNewWorld() {
        showEditor();
    }
}
