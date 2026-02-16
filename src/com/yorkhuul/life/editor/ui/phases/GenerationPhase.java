package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.pipeline.GenerationPipeline;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.editor.ui.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;

public interface GenerationPhase {

    String getName();

    PhaseType getType(); // FOUNDATION, GEOLOGY, HYDROLOGY...

    boolean isIterative();

    default void execute(EditorContext context) {
        World world = context.getWorld();
        GenerationPipeline pipeline = new GenerationPipeline();

        pipeline.run(context.getCurrentSteps(getType()), world, getName());
    }

    void invalidate(EditorContext context);

    void initialize(EditorContext context);
}
