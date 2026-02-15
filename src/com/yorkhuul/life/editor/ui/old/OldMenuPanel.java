package com.yorkhuul.life.editor.ui.old;

import com.yorkhuul.life.core.engine.context.EditorContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OldMenuPanel extends JPanel implements Screen {

    private JPanel contentPane;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JButton newButton;
    private JButton loadButton;

    private OldMainFrame window;
    private EditorContext context;

    public OldMenuPanel(OldMainFrame mainFrame, EditorContext context) {
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
