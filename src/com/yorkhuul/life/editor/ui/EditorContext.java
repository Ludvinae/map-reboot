package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.pipeline.StepConfig;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.core.engine.pipeline.foundation.NoiseConfig;
import com.yorkhuul.life.editor.ui.phases.PhaseType;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditorContext {

    private World world;
    private WorldConfig worldConfig;

    private Map<PhaseType, List<StepExecution<?>>> phaseExecutions = new HashMap<>();

    //private Deck<World> undoStack;
    //private Deck<World> redoStack;


    public World getWorld() {
        return world;
    }

    public WorldConfig getWorldConfig() {
        return worldConfig;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setWorldConfig(WorldConfig worldConfig) {
        this.worldConfig = worldConfig;
    }

    public List<StepExecution<?>> getCurrentSteps(PhaseType phaseType) {
        return phaseExecutions.get(phaseType);
    }

    public void addSteps(PhaseType phaseType, List<StepExecution<?>> steps) {
        phaseExecutions.put(phaseType, steps);
    }
}
