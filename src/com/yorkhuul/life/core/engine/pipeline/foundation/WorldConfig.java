package com.yorkhuul.life.core.engine.pipeline.foundation;

import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.clamp;

public class WorldConfig implements StepConfig {

    private String name;
    private String seed;
    private int width;
    private int height;

    public WorldConfig() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = clamp(width, 8, 64);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = clamp(height, 8, 64);
    }


    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> params = new ArrayList<>();



        return params;
    }
}
