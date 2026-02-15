package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.editor.ui.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class FoundationPhase implements GenerationPhase{

    List<StepExecution<?>> executions;

    public FoundationPhase() {
        executions = new ArrayList<>();
    }

    public void addExecution(StepExecution<?> execution) {
        executions.add(execution);
    }

    @Override
    public boolean isIterative() {
        return false;
    }

    @Override
    public void execute(EditorContext context) {
        WorldConfig config = context.getWorldConfig();
        World world = new World(config);

        context.setWorld(world);

        for(StepExecution<?> execution : executions){
            execution.execute(world);
        }
    }

    @Override
    public List<Parameter<?>> createParameters(EditorContext context) {
        // Parameters are created in the relevant step createParameter(config) method atm
        return List.of();
    }

    @Override
    public void invalidate(EditorContext context) {
        context.setWorld(null);
    }

    @Override
    public String getName() {
        return "Foundation";
    }

    @Override
    public PhaseType getType() {
        return PhaseType.FOUNDATION;
    }


}
