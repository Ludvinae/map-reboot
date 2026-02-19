package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.pipeline.GenerationPipeline;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.core.engine.pipeline.foundation.Noise;
import com.yorkhuul.life.core.engine.pipeline.foundation.NoiseConfig;
import com.yorkhuul.life.core.engine.pipeline.foundation.WorldGen;
import com.yorkhuul.life.editor.context.EditorContext;
import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class FoundationPhase implements GenerationPhase{

    @Override
    public void initialize(EditorContext context) {
        List<StepExecution<?>> steps = new ArrayList<>();

        WorldConfig worldConfig = new WorldConfig();
        context.setWorldConfig(worldConfig);
        steps.add(new StepExecution<>(new WorldGen(), worldConfig));

        NoiseConfig config = new NoiseConfig();
        steps.add(new StepExecution<>(new Noise(), config));

        context.addSteps(getType(), steps);
    }


    @Override
    public boolean isIterative() {
        return false;
    }

    @Override
    public void execute(EditorContext context) {
        WorldConfig config = context.getWorldConfig();
        //System.out.println(config);

        World world = new World(config);
        //System.out.println(world);
        context.setWorld(world);

        GenerationPipeline pipeline = new GenerationPipeline();
        pipeline.run(context.getCurrentSteps(getType()), world, getName());
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
