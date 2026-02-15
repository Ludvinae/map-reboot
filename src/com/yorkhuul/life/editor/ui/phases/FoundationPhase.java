package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.context.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.foundation.Noise;
import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.core.world.World;

import java.util.List;

public class FoundationPhase implements GenerationPhase{

    @Override
    public boolean isIterative() {
        return false;
    }

    @Override
    public void execute(EditorContext context) {
        WorldConfig config = context.getWorldConfig();
        World world = new World(config);

        context.setWorld(world);
        new Noise().apply(world, context.getNoiseConfig());
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
