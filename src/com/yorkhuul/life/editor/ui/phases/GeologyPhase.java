package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.pipeline.GenerationPipeline;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.core.engine.pipeline.geology.*;
import com.yorkhuul.life.editor.ui.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class GeologyPhase implements GenerationPhase {

    @Override
    public void initialize(EditorContext context) {
        List<StepExecution<?>> steps = new ArrayList<>();

        BorderConfig borderConfig = new BorderConfig();
        steps.add(new StepExecution<>(new OceanBorders(), borderConfig));

        TectonicConfig tectonicConfig =  new TectonicConfig();
        steps.add(new StepExecution<>(new Tectonic(), tectonicConfig));

        VarianceConfig varianceConfig = new VarianceConfig();
        steps.add(new StepExecution<>(new TileVariance(), varianceConfig));

        VolcanicConfig volcanicConfig = new VolcanicConfig();
        steps.add(new StepExecution<>(new Volcanic(), volcanicConfig));

        context.addSteps(getType(), steps);
    }

    @Override
    public String getName() {
        return "Geology";
    }

    @Override
    public PhaseType getType() {
        return PhaseType.GEOLOGY;
    }

    @Override
    public boolean isIterative() {
        return true;
    }

    @Override
    public List<Parameter<?>> createParameters(EditorContext context) {
        return List.of();
    }

    @Override
    public void invalidate(EditorContext context) {
        //context.clearGeologyState();
    }
}
