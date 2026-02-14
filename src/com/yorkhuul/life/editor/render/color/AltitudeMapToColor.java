package com.yorkhuul.life.editor.render.color;

import java.awt.*;

public class AltitudeMapToColor implements MapToColor {

    public Color getColor(float value) {
        if (value < -0.9) return new Color(0, 0, 0);
        else if (value < -0.8) return new Color(13, 13, 13);
        else if (value < -0.7) return new Color(27, 27, 27);
        else if (value < -0.6) return new Color(40, 40, 40);
        else if (value < -0.5) return new Color(54, 54, 54);
        else if (value < -0.4) return new Color(67, 67, 67);
        else if (value < -0.3) return new Color(81, 81, 81);
        else if (value < -0.2) return new Color(94, 94, 94);
        else if (value < -0.1) return new Color(107, 107, 107);
        else if (value < 0) return new Color(121, 121, 121);
        else if (value < 0.1) return new Color(134, 134, 134);
        else if (value < 0.2) return new Color(148, 148, 148);
        else if (value < 0.3) return new Color(161, 161, 161);
        else if (value < 0.4) return new Color(174, 174, 174);
        else if (value < 0.5) return new Color(188, 188, 188);
        else if (value < 0.6) return new Color(201, 201, 201);
        else if (value < 0.7) return new Color(215, 215, 215);
        else if (value < 0.8) return new Color(228, 228, 228);
        else if (value < 0.9) return new Color(242, 242, 242);
        else return new Color(255, 255, 255);
    }
}
