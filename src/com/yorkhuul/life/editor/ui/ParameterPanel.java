package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.parameters.CheckParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.editor.ui.components.ParameterComponentFactory;
import com.yorkhuul.life.editor.ui.components.RoundedButton;
import com.yorkhuul.life.editor.ui.components.StepPanel;

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
        //parameterContainer.setPreferredSize(new Dimension(250, -1));

        generateButton = new RoundedButton("Generate");
        //JPanel bottomPanel = new JPanel();
        //bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //bottomPanel.add(generateButton);
        add(generateButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(parameterContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

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


    protected void addPanel(StepExecution<?> execution) {
        StepPanel panel = new StepPanel(execution);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        parameterContainer.add(panel);
    }

    protected void removeAllPanels() {
        parameterContainer.removeAll();
        revalidate();
        repaint();
    }

    protected void gluePanels() {
        parameterContainer.add(Box.createVerticalGlue());
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
