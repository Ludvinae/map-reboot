package com.yorkhuul.life.editor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private MainFrame window;
    private JPanel contentPane;

    private JButton newButton;
    private JButton loadButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MenuPanel(MainFrame window) {
        this.window = window;
        this.contentPane = new JPanel();

        this.newButton = new JButton("Create a new world");
        this.loadButton = new JButton("Load world");
        this.optionsButton = new JButton("Options");
        this.exitButton = new JButton("Exit");

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

    public void onNew() {
        window.showEditor();
    }

    public void onLoad() {}

    public void onOptions() {}

    public void onExit() {}

    private void buildLayout() {
        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);
        contentPane.add(newButton);
        contentPane.add(loadButton);
        contentPane.add(optionsButton);
        contentPane.add(exitButton);
    }
}
