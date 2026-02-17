package com.yorkhuul.life.editor.ui.components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    public RoundedPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setPreferredSize(new Dimension(300, 400));
        setMaximumSize(new Dimension(300, 400));
        setMinimumSize(new Dimension(300, 400));

        setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 25, 25);

        // Background
        g2.setColor(new Color(230, 230, 230));
        g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 25, 25);

        // Border
        g2.setColor(new Color(180, 180, 180));
        g2.drawRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 25, 25);

        g2.dispose();
    }
}
