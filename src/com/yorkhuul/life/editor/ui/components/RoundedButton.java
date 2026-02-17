package com.yorkhuul.life.editor.ui.components;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);

        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setBackground(new Color(210, 210, 210));
        setForeground(Color.BLACK);

        setPreferredSize(new Dimension(200, 50));
        setMaximumSize(new Dimension(200, 50));
        setMinimumSize(new Dimension(200, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow
        g2.setColor(new Color(80, 80, 80, 40));
        g2.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 20, 20);

        // Background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 20, 20);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(127, 127, 127));
        g2.drawRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 20, 20);

        g2.dispose();
    }
}
