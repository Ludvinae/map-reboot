package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.context.EditorContext;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout layout;
    private final JPanel mainPanel;

    private MenuPanel MenuPanel;
    private EditorPanel EditorPanel;
    private String MAIN_MENU = "MENU";
    private String EDITOR = "EDITOR";

    private EditorContext context;

    public MainFrame() {
        this.layout = new CardLayout();
        this.mainPanel = new JPanel(layout);

        context = new EditorContext();
        this.MenuPanel = new MenuPanel(this);
        this.EditorPanel = new EditorPanel(context);

        mainPanel.add(MAIN_MENU, MenuPanel);
        mainPanel.add(EDITOR, EditorPanel);

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);

    }


    public void showMenu() {
        layout.show(mainPanel, MAIN_MENU);
    }

    public void showEditor() {
        layout.show(mainPanel, EDITOR);
    }
}
