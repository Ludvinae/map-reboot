package com.yorkhuul.life.map.context;

import com.yorkhuul.life.map.context.config.StepConfig;
import com.yorkhuul.life.map.context.config.WorldConfig;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class EditorContext {

    // Configuration choisie par l'utilisateur
    private WorldConfig worldConfig;
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

    public World getWorld() {return world;}

    public void setWorld(World world) {this.world = world;}

    public List<StepConfig> getStepConfigs() {return stepConfigs;}

    public void setStepConfigs(List<StepConfig> stepConfigs) {this.stepConfigs = stepConfigs;}
}
