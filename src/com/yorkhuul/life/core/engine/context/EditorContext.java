package com.yorkhuul.life.core.engine.context;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;
import com.yorkhuul.life.core.engine.pipeline.WorldConfig;
import com.yorkhuul.life.core.engine.pipeline.geology.NoiseConfig;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class EditorContext {

    // Configuration choisie par l'utilisateur
    private WorldConfig worldConfig;
    private NoiseConfig noiseConfig;
    // Monde généré (ou en cours)
    private World world;
    // Pipeline de génération
    private List<StepConfig> stepConfigs = new ArrayList<>();
    // Etat UI
    private int currentStepIndex = -1;
    // Services partagés
    private NoiseService noiseService;


    public EditorContext() {}


    public WorldConfig getWorldConfig() {return worldConfig;}

    public void setWorldConfig(WorldConfig worldConfig) {this.worldConfig = worldConfig;}

    public NoiseConfig getNoiseConfig() {
        return noiseConfig;
    }

    public void setNoiseConfig(NoiseConfig noiseConfig) {
        this.noiseConfig = noiseConfig;
    }

    public World getWorld() {
        if (world == null) setWorld(new World(getWorldConfig()));

        return world;
    }

    public void setWorld(World world) {this.world = world;}

    public List<StepConfig> getStepConfigs() {return stepConfigs;}

    public void setStepConfigs(List<StepConfig> stepConfigs) {this.stepConfigs = stepConfigs;}

    public void addToConfigList(StepConfig stepConfig) {this.stepConfigs.add(stepConfig);}
}
