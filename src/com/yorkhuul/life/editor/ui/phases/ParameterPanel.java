package com.yorkhuul.life.editor.ui.phases;

import com.yorkhuul.life.core.engine.parameters.Parameter;
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

}
