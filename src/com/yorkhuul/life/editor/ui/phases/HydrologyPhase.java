package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.context.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.hydrology.Rain;
import com.yorkhuul.life.core.engine.pipeline.hydrology.WaterErosion;
import com.yorkhuul.life.core.engine.pipeline.hydrology.WaterFlow;
import com.yorkhuul.life.core.engine.pipeline.hydrology.WaterLevelOutflow;
import com.yorkhuul.life.core.world.World;

import java.util.List;
import java.util.concurrent.Flow;

public class HydrologyPhase implements GenerationPhase{
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
    public void execute(EditorContext context) {
        World world = context.getWorld();

        new Rain().apply(world, context.getRainConfig());
        new WaterFlow().apply(world, context.getFlowConfig());
        new WaterLevelOutflow(world, context.getOutflowConfig());
        new WaterErosion(world, context.getErosionConfig());
    }

    @Override
    public List<Parameter<?>> createParameters(EditorContext context) {
        return List.of();
    }

    @Override
    public void invalidate(EditorContext context) {
        context.clearHydrologyState();
    }
}
