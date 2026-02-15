package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.context.EditorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhaseManager {

    private EditorContext context;

    private final List<GenerationPhase> phases;
    private int currentPhaseIndex;

    private Map<PhaseType, PhaseState> phaseStates;

    public PhaseManager(EditorContext context) {
        this.context = context;
        // not sure on this part yet
        phases = new ArrayList<>();
        phases.add(new FoundationPhase());
        phases.add(new GeologyPhase());
        phases.add(new HydrologyPhase());
    }

    public GenerationPhase getCurrentPhase() {
        return phases.get(currentPhaseIndex);
    }

    public void next() {
        currentPhaseIndex++;
    }

    public void back() {
        invalidateFrom(currentPhaseIndex);
        currentPhaseIndex--;
    }

    public void executeCurrent(EditorContext context) {
        GenerationPhase phase = phases.get(currentPhaseIndex);
        phase.execute(context);
        invalidateAfter(currentPhaseIndex);
    }

    private void invalidateFrom(int index) {
        for (int i = index; i < phases.size(); i++) {
            phases.get(i).invalidate(context);
        }
    }

    private void invalidateAfter(int index) {
        for (int i = index + 1; i < phases.size(); i++) {
            phases.get(i).invalidate(context);
        }
    }
}
