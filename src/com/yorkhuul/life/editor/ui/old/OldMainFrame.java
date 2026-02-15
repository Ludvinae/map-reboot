package com.yorkhuul.life.editor.ui.old;

import com.yorkhuul.life.core.engine.context.EditorContext;

import javax.swing.*;
import java.awt.*;

public class OldMainFrame extends JFrame {

    private final EditorContext context;

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private final OldMenuPanel oldMenuPanel;
    private final WorldGenPanel worldGenPanel;
    private final PipelinePanel pipelinePanel;

    public static final String MENU = "menu";
    public static final String WORLD_GEN = "WORLD_GEN";
    public static final String PIPELINE = "PIPELINE";

    public OldMainFrame() {

        this.context = new EditorContext();

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);

        // Panels
        this.oldMenuPanel = new OldMenuPanel(this, context);
        this.worldGenPanel = new WorldGenPanel(this, context);
        this.pipelinePanel = new PipelinePanel(this, context);

        mainPanel.add(oldMenuPanel, MENU);
        mainPanel.add(worldGenPanel, WORLD_GEN);
        mainPanel.add(pipelinePanel, PIPELINE);

        setContentPane(mainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);

        showMenu();
    }

    private void showPanel(String name, Screen screen) {
        cardLayout.show(mainPanel, name);
        screen.onDisplayed();
    }

    public void showWorldGen() {
        showPanel(WORLD_GEN, worldGenPanel);
    }

    public void showPipeline() {
        showPanel(PIPELINE, pipelinePanel);
    }

    public void showMenu() {
        showPanel(MENU, oldMenuPanel);
    }


}
