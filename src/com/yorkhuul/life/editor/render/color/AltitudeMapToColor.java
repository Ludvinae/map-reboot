package com.yorkhuul.life.editor.render.color;

import java.awt.*;

public class AltitudeMapToColor implements MapToColor {

    @Override
    public Color getColor(float value) {
        if (value < -0.8) {
            return new Color(0, 17, 26);
        } else if (value < -0.6) {
            return new Color(1, 42, 65);
        } else if (value < -0.4) {
            return new Color(1, 76, 117);
        } else if (value < -0.2) {
            return new Color(142, 235, 237);
        } else if (value < 0) {
            return new Color(226, 202, 118);
        } else if (value < 0.2) {
            return new Color(63, 155, 11);
        } else if (value < 0.4) {
            return new Color(134, 181, 4);
        } else if (value < 0.6) {
            return new Color(122, 77, 58);
        } else if (value < 0.8) {
            return new Color(17, 17, 30);
        } else if (value <= 1) {
            return new Color(255, 250, 250);
        } else {
            // indicates problem in altitude value
            return new Color(255, 0, 0);
        }
    }
}
