package com.yorkhuul.life.map.context;

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
    private List<GenerationStep> steps = new ArrayList<>();

    // Etat UI
    private int currentStepIndex = -1;

    // Services partagés
    private NoiseService noiseService;
}
