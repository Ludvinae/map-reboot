package com.yorkhuul.life.editor.ui.components;

import com.yorkhuul.life.core.engine.parameters.Parameter;

import javax.swing.*;
import java.awt.*;

public class ParameterComponentFactory {

    public static JComponent create(Parameter<?> parameter) {

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel nameLabel = new JLabel(parameter.getName());

        JSlider slider = new JSlider(
                parameter.getMin(),
                parameter.getMax(),
                parameter.getInitial()
        );

        slider.setPreferredSize(new Dimension(150, 30));
        slider.setPaintTicks(true);
        slider.setPaintLabels(false);

        JLabel valueLabel = new JLabel(
                parameter.formatValue(parameter.getInitial())
        );

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            parameter.updateFromSlider(value);
            valueLabel.setText(parameter.formatValue(value));
        });

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(valueLabel, BorderLayout.EAST);

        return panel;
    }
}
