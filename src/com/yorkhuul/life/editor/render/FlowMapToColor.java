package com.yorkhuul.life.editor.render;

import com.yorkhuul.life.core.world.WorldQueries;

import java.awt.Color;

public class FlowMapToColor implements MapToColor {


    @Override
    public Color getColor(float value) {
        float v = (float)Math.log1p(value) / (float)Math.log1p(WorldQueries.getMaxCumulativeFlow());
        v = Math.min(1f, v);

        // Bleu → Cyan → Blanc
        int r = (int)(255 * v);
        int g = (int)(255 * v);
        int b = 255;

        return new Color(r, g, b);
    }
}
