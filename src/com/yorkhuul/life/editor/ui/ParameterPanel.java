package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.parameters.CheckParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.editor.ui.components.ParameterComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        parameterContainer.setPreferredSize(new Dimension(300, -1));

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

        JPanel mainPanel = new JPanel(new BorderLayout());

        // ===== CONTENT PANEL =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));

        for (Parameter<?> parameter : execution.createParameters()) {
            contentPanel.add(ParameterComponentFactory.create(parameter));
        }

        // ===== HEADER =====
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JLabel arrowLabel = new JLabel("▼");

        JCheckBox enabledCheckBox;
        final boolean[] collapsed = { false };

        if (execution.isOptional()) {
            enabledCheckBox = new JCheckBox();
            enabledCheckBox.setSelected(execution.isEnabled());
            enabledCheckBox.addActionListener(e -> {
                boolean enabled = enabledCheckBox.isSelected();
                execution.setEnabled(enabled);
                if (!enabled) {
                    collapsed[0] = true;
                }
                updateVisibility(execution, contentPanel, arrowLabel, collapsed[0]);

                mainPanel.revalidate();
            });
        } else {
            enabledCheckBox = null;
        }

        JLabel nameLabel = new JLabel(execution.getStep().getName());
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 16));

        // ===== COLLAPSE STATE =====

        arrowLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        arrowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                collapsed[0] = !collapsed[0];
                updateVisibility(execution, contentPanel, arrowLabel, collapsed[0]);

                boolean visible = !collapsed[0] &&
                        (!execution.isOptional() || execution.isEnabled());

                contentPanel.setVisible(visible);
                arrowLabel.setText(collapsed[0] ? "▶" : "▼");

                mainPanel.revalidate();
            }
        });
        // force parent layout refresh
        SwingUtilities.invokeLater(() -> {
            mainPanel.revalidate();
            mainPanel.repaint();
        });


        // ===== ASSEMBLY =====
        headerPanel.add(arrowLabel);

        if (enabledCheckBox != null) {
            headerPanel.add(enabledCheckBox);
        }

        headerPanel.add(nameLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // état initial cohérent
        if (execution.isOptional() && !execution.isEnabled()) {
            contentPanel.setVisible(false);
        }

        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerPanel.setBackground(new Color(172, 172, 172));
        headerPanel.setPreferredSize(new Dimension(-1, 30));

        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        return mainPanel;
    }


    private void updateVisibility(StepExecution<?> execution, JPanel contentPanel,
                                  JLabel arrowButton, boolean collapsed) {
        boolean visible = !collapsed && (!execution.isOptional() || execution.isEnabled());

        contentPanel.setVisible(visible);
        arrowButton.setText(visible ? "▼" : "▶");
    }


    /*
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

        if (execution.isOptional()) {
            CheckParameter enabledParam = new CheckParameter("Enabled", execution.isEnabled(), execution::setEnabled);
            contentPanel.add(ParameterComponentFactory.create(enabledParam));
        }

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

     */

    protected void addPanel(JPanel stepPanel) {
        parameterContainer.add(stepPanel);
    }

    protected void removeAllPanels() {
        parameterContainer.removeAll();
        revalidate();
        repaint();
    }

}
