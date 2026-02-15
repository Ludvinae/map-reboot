package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.editor.ui.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.geology.Tectonic;
import com.yorkhuul.life.core.world.World;

import java.util.List;

public class GeologyPhase implements GenerationPhase {
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
    public void execute(EditorContext context) {
        World world = context.getWorld();

        // needs to be replaced with proper storage of config files in context
        //new Tectonic().apply(world, context.getTectonicConfig);
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
