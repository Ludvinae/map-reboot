package com.yorkhuul.life.editor.ui.components;

import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StepPanel extends JPanel {

    private final StepExecution<?> execution;

    private final JPanel contentPanel;
    private JLabel arrowLabel;

    private boolean collapsed = false;

    public StepPanel(StepExecution<?> execution) {

        this.execution = execution;

        setLayout(new BorderLayout());
        setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel = buildContent();
        JPanel headerPanel = buildHeader();

        add(headerPanel, BorderLayout.NORTH);
        //add(contentPanel, BorderLayout.CENTER);

        // utilité discutable
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setBackground(Color.BLUE);


        updateVisibility();
  }

    private JPanel buildContent() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (Parameter<?> parameter : execution.createParameters()) {
            panel.add(ParameterComponentFactory.create(parameter));
        }

        return panel;
    }

    private JPanel buildHeader() {

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
        header.setBackground(new Color(172, 172, 172));

        arrowLabel = new JLabel("▼");
        arrowLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        arrowLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        arrowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                collapsed = !collapsed;
                updateVisibility();
            }
        });

        header.add(arrowLabel);

        if (execution.isOptional()) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(execution.isEnabled());
            checkBox.setAlignmentY(Component.CENTER_ALIGNMENT);

            checkBox.addActionListener(e -> {
                execution.setEnabled(checkBox.isSelected());
                if (!execution.isEnabled()) {
                    collapsed = true;
                }
                updateVisibility();
            });

            header.add(Box.createHorizontalStrut(5));
            header.add(checkBox);
        }

        JLabel nameLabel = new JLabel(execution.getStep().getName());
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 16f));
        nameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        header.add(Box.createHorizontalStrut(8));
        header.add(nameLabel);

        return header;
    }

    private void updateVisibility() {
        boolean shouldShow = !collapsed && (!execution.isOptional() || execution.isEnabled());

        if (shouldShow) {
            if (contentPanel.getParent() == null) {
                add(contentPanel, BorderLayout.CENTER);
            }
            arrowLabel.setText("▼");
        } else {
            remove(contentPanel);
            arrowLabel.setText("▶");
        }

        revalidate();
        repaint();
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(
                Integer.MAX_VALUE,
                getPreferredSize().height
        );
    }

}
