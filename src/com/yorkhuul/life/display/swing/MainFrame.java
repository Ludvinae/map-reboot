package com.yorkhuul.life.display.swing;

import com.yorkhuul.life.map.context.EditorContext;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final EditorContext context;

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private final MenuPanel menuPanel;
    private final WorldGenPanel worldGenPanel;
    private final PipelinePanel pipelinePanel;

    public static final String MENU = "menu";
    public static final String WORLD_GEN = "WORLD_GEN";
    public static final String PIPELINE = "PIPELINE";

    public MainFrame() {

        this.context = new EditorContext();

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);

        // Panels
        this.menuPanel = new MenuPanel(this, context);
        this.worldGenPanel = new WorldGenPanel(this, context);
        this.pipelinePanel = new PipelinePanel(this, context);

        mainPanel.add(menuPanel, MENU);
        mainPanel.add(worldGenPanel, WORLD_GEN);
        mainPanel.add(pipelinePanel, PIPELINE);

        setContentPane(mainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);

        showWorldGen();
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
        showPanel(MENU, menuPanel);
    }


}
