package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.editor.ui.EditorContext;
import com.yorkhuul.life.core.engine.parameters.Parameter;

import java.util.List;

public interface GenerationPhase {

    String getName();

    PhaseType getType(); // FOUNDATION, GEOLOGY, HYDROLOGY...

    boolean isIterative();

    void execute(EditorContext context);

    List<Parameter<?>> createParameters(EditorContext context);

    void invalidate(EditorContext context);
}
