package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.editor.ui.components.ParameterComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ParameterPanel extends JPanel {

    private JPanel parameterContainer;
    private JButton generateButton;

    public ParameterPanel() {
        build();
    }

    private void build() {
        setLayout(new BorderLayout());

        parameterContainer = new JPanel();
        parameterContainer.setLayout(new BoxLayout(parameterContainer, BoxLayout.Y_AXIS));

        generateButton = new JButton("Generate");

        add(new JScrollPane(parameterContainer), BorderLayout.CENTER);
        add(generateButton, BorderLayout.SOUTH);
    }

    public void loadParameters(List<Parameter<?>> params) {
        parameterContainer.removeAll();

        for (Parameter<?> param : params) {
            parameterContainer.add(ParameterComponentFactory.create(param));
        }

        revalidate();
        repaint();
    }

    public void setOnGenerate(Runnable action) {
        generateButton.addActionListener(e -> action.run());
    }

    protected JPanel buildStepPanel(StepExecution<?> execution) {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // ===== HEADER =====
        JButton headerButton = new JButton("▼ " + execution.getStep().getName());
        headerButton.setFocusPainted(false);
        headerButton.setBorderPainted(false);
        headerButton.setContentAreaFilled(false);
        headerButton.setHorizontalAlignment(SwingConstants.LEFT);

        // ===== CONTENT =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

        for (Parameter<?> parameter : execution.createParameters()) {
            contentPanel.add(ParameterComponentFactory.create(parameter));
        }

        // ===== COLLAPSE STATE =====
        final boolean[] collapsed = { false };

        headerButton.addActionListener(e -> {
            collapsed[0] = !collapsed[0];
            contentPanel.setVisible(!collapsed[0]);

            headerButton.setText(
                    (collapsed[0] ? "▶ " : "▼ ")
                            + execution.getStep().getName()
            );

            mainPanel.revalidate();
            mainPanel.repaint();
        });

        mainPanel.add(headerButton, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    protected void addPanel(JPanel stepPanel) {
        parameterContainer.add(stepPanel);
    }

    protected void removeAllPanels() {
        parameterContainer.removeAll();
        revalidate();
        repaint();
    }

}
