package com.yorkhuul.life.editor.render;

import java.awt.*;

public class RiverMapToColor implements MapToColor {
    @Override
    public Color getColor(float value) {
        if (value <= 0.005) return new Color(255, 255, 255);
        if (value <= 0.1) return new Color (150, 175, 225);
        if (value <= 0.5) return new Color(50, 100, 175);
        return new Color(0, 25, 100);
    }
}
