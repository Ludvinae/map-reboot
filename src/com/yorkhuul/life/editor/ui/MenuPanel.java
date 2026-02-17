package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.editor.ui.components.RoundedButton;
import com.yorkhuul.life.editor.ui.components.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private MainFrame window;
    private RoundedPanel menuPanel;

    private JButton newButton;
    private JButton loadButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MenuPanel(MainFrame window) {
        this.window = window;
        this.menuPanel = new RoundedPanel();

        this.newButton = createButton("Create a new world");
        this.loadButton = createButton("Load world");
        this.optionsButton = createButton("Options");
        this.exitButton = createButton("Exit");

        buildLayout();

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

        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOptions();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });
    }

    private JButton createButton(String text) {
        RoundedButton button = new RoundedButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    public void onNew() {
        window.startNewWorld();
    }

    public void onLoad() {}

    public void onOptions() {}

    public void onExit() {}

    private void buildLayout() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        menuPanel.add(newButton);
        menuPanel.add(Box.createVerticalStrut(25));
        menuPanel.add(loadButton);
        menuPanel.add(Box.createVerticalStrut(25));
        menuPanel.add(optionsButton);
        menuPanel.add(Box.createVerticalStrut(25));
        menuPanel.add(exitButton);

        add(menuPanel);
    }

}
