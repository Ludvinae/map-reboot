package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.core.engine.pipeline.hydrology.*;
import com.yorkhuul.life.editor.ui.EditorContext;

import java.util.ArrayList;
import java.util.List;


public class HydrologyPhase implements GenerationPhase{

    @Override
    public void initialize(EditorContext context) {
        List<StepExecution<?>> steps = new ArrayList<>();

        RainConfig rainConfig = new RainConfig();
        steps.add(new StepExecution<>(new Rain(), rainConfig));

        FlowConfig  flowConfig = new FlowConfig();
        steps.add(new StepExecution<>(new WaterFlow(), flowConfig));

        OutflowConfig outflowConfig = new OutflowConfig();
        steps.add(new StepExecution<>(new WaterLevelOutflow(), outflowConfig));

        ErosionConfig erosionConfig = new ErosionConfig();
        steps.add(new StepExecution<>(new WaterErosion(), erosionConfig));

        context.addSteps(getType(), steps);
    }

    @Override
    public String getName() {
        return "Hydrology";
    }

    @Override
    public PhaseType getType() {
        return PhaseType.HYDROLOGY;
    }

    @Override
    public boolean isIterative() {
        return true;
    }

    @Override
    public void invalidate(EditorContext context) {
        //context.clearHydrologyState();
    }
}
