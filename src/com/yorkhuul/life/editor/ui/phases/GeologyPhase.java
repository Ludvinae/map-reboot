package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.context.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;

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

    }

    @Override
    public List<Parameter<?>> createParameters(EditorContext context) {
        return List.of();
    }

    @Override
    public void invalidate(EditorContext context) {

    }
}
