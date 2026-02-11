package com.yorkhuul.life.display.swing.parameters;

import javax.swing.*;
import java.awt.*;


public class ParametersPanel extends JPanel {
    private int currentRow = 0;

    public ParametersPanel() {
        setLayout(new GridBagLayout());
    }

    public void addParameter(Parameter<?> parameter) {

        JLabel nameLabel = new JLabel(parameter.getName());
        JSlider slider = new JSlider(
                parameter.getMin(),
                parameter.getMax(),
                parameter.getInitial()
        );
        JLabel valueLabel = new JLabel(parameter.formatValue(parameter.getInitial()));

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            parameter.updateFromSlider(value);
            valueLabel.setText(parameter.formatValue(value));
        });

        // Layout code ici...
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = currentRow;

        // Label nom
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(nameLabel, gbc);

        // Slider
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(slider, gbc);

        // Label valeur
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(valueLabel, gbc);

        currentRow++;
    }

}
