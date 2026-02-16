package com.yorkhuul.life.editor.ui.components;

import com.yorkhuul.life.core.engine.parameters.CheckParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.parameters.StringParameter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ParameterComponentFactory {

    public static JComponent create(Parameter<?> parameter) {
        if (parameter instanceof StringParameter stringParameter) {
            return createTextField(stringParameter);
        }
        if (parameter instanceof CheckParameter checkParameter) {
            return createCheckBox(checkParameter);
        }
        return createSlider(parameter);
    }

    private static JComponent createSlider(Parameter<?> parameter) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(parameter.getName());
        JSlider slider = new JSlider(parameter.getMin(), parameter.getMax(),
                        parameter.getInitial());

        JLabel valueLabel = new JLabel(parameter.formatValue(parameter.getInitial()));

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            parameter.updateFromSlider(value);
            valueLabel.setText(parameter.formatValue(value));
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(valueLabel, BorderLayout.SOUTH);

        return panel;
    }


    private static JComponent createTextField(StringParameter parameter) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(parameter.getName());
        JTextField textField =
                new JTextField(parameter.getInitialValue());

        textField.getDocument().addDocumentListener(new DocumentListener() {

            private void update() {
                parameter.updateFromText(textField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    private static JComponent createCheckBox(CheckParameter parameter) {

        JCheckBox checkBox = new JCheckBox(
                parameter.getName(),
                parameter.getInitialValue()
        );

        checkBox.addActionListener(e ->
                parameter.updateFromCheck(checkBox.isSelected())
        );

        return checkBox;
    }
}
