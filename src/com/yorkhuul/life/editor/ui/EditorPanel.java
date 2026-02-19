package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.editor.context.EditorContext;
import com.yorkhuul.life.editor.ui.phases.PhaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class EditorPanel extends JPanel {

    private final PhaseManager phaseManager;
    private final EditorContext context;

    private TopBarPanel topBar;
    private MapPanel mapPanel;
    private ParameterPanel parameterPanel;

    public EditorPanel(EditorContext context) {
        this.context = context;
        this.phaseManager = new PhaseManager(context);


        initialize();
        buildLayout();
        wireEvents();
        refreshUI();
    }

    public void initialize() {
        topBar = new TopBarPanel();
        mapPanel = new MapPanel();
        parameterPanel = new ParameterPanel();

    }

    private void buildLayout() {
        setLayout(new BorderLayout());

        add(topBar, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);

        parameterPanel.setPreferredSize(new Dimension(300, 0));
        add(parameterPanel, BorderLayout.EAST);
    }

    private void wireEvents() {

        topBar.setOnNext(this::handleNext);
        topBar.setOnBack(this::handleBack);

        parameterPanel.setOnGenerate(this::handleGenerate);
    }

    private void handleGenerate() {
        phaseManager.executeCurrent(context);
        refreshMap();
        refreshNavigationState();
    }

    private void handleNext() {
        if (phaseManager.canMoveNext()) {
            phaseManager.next();
            refreshUI();
        }
    }

    private void handleBack() {
        phaseManager.back();
        refreshUI();
    }

    private void refreshUI() {
        refreshPhaseTitle();
        refreshParameters();
        refreshNavigationState();
        refreshMap();
    }

    private void refreshPhaseTitle() {
        topBar.setTitle(
                phaseManager.getCurrentPhase().getName()
        );
    }

    private void refreshParameters() {
        List<StepExecution<?>> executions = context.getCurrentSteps(phaseManager.getCurrentPhaseType());

        // Clean up old parameters tabs
        parameterPanel.removeAllPanels();

        for (StepExecution<?> execution : executions) {
            parameterPanel.addPanel(execution);
        }
        //parameterPanel.gluePanels();
    }

    private void refreshNavigationState() {
        topBar.updateNavigationButtons(phaseManager);
    }

    private void refreshMap() {
        mapPanel.setWorld(context.getWorld());

    }
}

