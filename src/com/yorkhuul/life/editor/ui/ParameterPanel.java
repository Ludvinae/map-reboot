package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.engine.parameters.CheckParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.StepExecution;
import com.yorkhuul.life.editor.ui.components.ParameterComponentFactory;
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


    protected void addPanel(StepExecution<?> execution) {
        parameterContainer.add(new StepPanel(execution));
    }

    protected void removeAllPanels() {
        parameterContainer.removeAll();
        revalidate();
        repaint();
    }

}
