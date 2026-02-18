package com.yorkhuul.life.core.engine.pipeline.foundation;

import com.yorkhuul.life.core.engine.parameters.*;
import com.yorkhuul.life.core.engine.pipeline.StepConfig;

import java.util.ArrayList;
import java.util.List;

public class NoiseConfig implements StepConfig {
    private float frequency = 0.0002f;
    private int OFFSET = 57;
    private float strength = 0.7f;
    private float lacunarity = 2f;
    private int nbOctaves = 3;
    private String fractalType = "None";

    // Temporarily divide frequency by 10000
    public float getFrequency() {
        return frequency;
    }

    public int getOffset() {
        return OFFSET;
    }

    // Temporarily divide strength by 100, should be removed once parameters are implemented fully
    public float getStrength() {
        return strength ;
    }

    public float getLacunarity() {
        return lacunarity;
    }

    public int getNbOctaves() {
        return nbOctaves;
    }

    public String getFractalType() {
        return fractalType;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public void setLacunarity(float lacunarity) {
        this.lacunarity = lacunarity;
    }

    public void setNbOctaves(int nbOctaves) {
        this.nbOctaves = nbOctaves;
    }

    public void setFractalType(String fractalType) {
        this.fractalType = fractalType;
    }

    @Override
    public String toString() {
        return "Frequency : "  + frequency + ", Strength : " + strength;
    }


    @Override
    public List<Parameter<?>> buildParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new LogParameter("Pattern repetition", 0.0001f, 0.1f, getFrequency(), 250, this::setFrequency));
        parameters.add(new FloatParameter("Altitude amplitude", 1, 100, getStrength(), 0.01f, this::setStrength));
        parameters.add(new StringParameter("Fractal type", getFractalType(), this::setFractalType));
        parameters.add(new FloatParameter("Lacunarity", 1, 20, getLacunarity(), 0.5f, this::setLacunarity));
        parameters.add(new IntParameter("Number of octaves", 1, 10, getNbOctaves(), this::setNbOctaves));

        return parameters;
    }
}
