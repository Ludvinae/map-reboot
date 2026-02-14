package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;

public class ErosionConfig implements StepConfig {
    private float sedimentCapacityCoefficient;
    private float maxErosionPerStep;
    private float strength;

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getMaxErosionPerStep() {
        return maxErosionPerStep;
    }

    public void setMaxErosionPerStep(float maxErosionPerStep) {
        this.maxErosionPerStep = maxErosionPerStep;
    }

    public float getSedimentCapacityCoefficient() {
        return sedimentCapacityCoefficient;
    }

    public void setSedimentCapacityCoefficient(float sedimentCapacityCoefficient) {
        this.sedimentCapacityCoefficient = sedimentCapacityCoefficient;
    }


}
