package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.context.EditorContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements Screen{

    private JPanel contentPane;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JButton newButton;
    private JButton loadButton;

    private MainFrame window;
    private EditorContext context;

    public MenuPanel(MainFrame mainFrame, EditorContext context) {
        this.window = mainFrame;
        this.context = context;

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
        window.showWorldGen();
    }

    private void onLoad() {
        // add your code here if necessary
        window.showMenu();
    }

    @Override
    public void onDisplayed() {

    }
}
