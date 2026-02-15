package com.yorkhuul.life.editor.ui.phases;

import javax.swing.*;
import java.awt.*;

public class TopBarPanel extends JPanel {

    private JButton backButton;
    private JLabel titleLabel;
    private JButton nextButton;


    public TopBarPanel() {
        build();
    }

    private void build() {
        setLayout(new BorderLayout());

        this.backButton = new JButton("< Back");
        this.titleLabel = new JLabel("", SwingConstants.CENTER);
        this.nextButton = new JButton("Next >");

        add(backButton, BorderLayout.WEST);
        add(titleLabel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.EAST);
    }

    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    public void setOnBack(Runnable action) {
        backButton.addActionListener(e -> action.run());
    }

    public void setBackEnabled(boolean enabled) {
        backButton.setEnabled(enabled);
    }

    public void setOnNext(Runnable action) {
        nextButton.addActionListener(e -> action.run());
    }

    public void setNextEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }
}
