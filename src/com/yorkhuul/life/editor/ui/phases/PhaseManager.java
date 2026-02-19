package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.editor.context.EditorContext;

import java.util.ArrayList;
import java.util.HashMap;
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

        registerPhases(context);
        createStateMap();
    }

    private void createStateMap() {
        phaseStates = new HashMap<>();
        for (GenerationPhase phase : phases) {
            phaseStates.put(phase.getType(), PhaseState.NOT_STARTED);
        }
    }

    private void registerPhases(EditorContext context) {
        for (GenerationPhase phase : phases) {
            phase.initialize(context);
        }
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
        phaseStates.replace(phase.getType(), PhaseState.GENERATED);
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

    public boolean canMoveNext() {
        return currentPhaseIndex < phases.size() - 1 && isCurrentGenerated();
    }

    public boolean canMoveBack() {
        return currentPhaseIndex > 0;
    }

    public boolean isCurrentGenerated() {
        GenerationPhase phase = phases.get(currentPhaseIndex);
        if (phase == null) return false;

        PhaseState state = phaseStates.get(phase.getType());
        if (state == PhaseState.GENERATED) return true;

        return false;
    }

    public PhaseType getCurrentPhaseType() {
        //System.out.println("current phase index: " + currentPhaseIndex);
        return getCurrentPhase().getType();
    }
}
