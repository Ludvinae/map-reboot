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
        JPanel panel = new JPanel();
        setupPanel(panel);

        // ===== Name label =====
        JLabel nameLabel = new JLabel(parameter.getName());
        setupNameLabel(nameLabel);
        panel.add(nameLabel);

        panel.add(Box.createVerticalStrut(6));

        // ===== Slider + Value row =====
        JPanel sliderRow = new JPanel();
        sliderRow.setLayout(new BoxLayout(sliderRow, BoxLayout.X_AXIS));
        sliderRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        sliderRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JSlider slider = new JSlider(
                parameter.getMin(),
                parameter.getMax(),
                parameter.getInitial()
        );
        slider.setMaximumSize(new Dimension(Integer.MAX_VALUE, slider.getPreferredSize().height));
        slider.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel valueLabel = new JLabel(
                parameter.formatValue(parameter.getInitial())
        );

        // some space so it doesn't move around
        valueLabel.setPreferredSize(new Dimension(50, 20));
        valueLabel.setMinimumSize(new Dimension(50, 20));
        valueLabel.setMaximumSize(new Dimension(50, 20));
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // dynamic update
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            parameter.updateFromSlider(value);
            valueLabel.setText(parameter.formatValue(value));
        });

        sliderRow.add(slider);
        sliderRow.add(Box.createHorizontalStrut(10));
        sliderRow.add(valueLabel);

        panel.add(sliderRow);

        // prevent panel to grow vertically
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));

        return panel;
    }


    private static JComponent createTextField(StringParameter parameter) {
        JPanel panel = new JPanel();
        setupPanel(panel);

        JLabel label = new JLabel(parameter.getName());
        setupNameLabel(label);
        panel.add(label);
        panel.add(Box.createVerticalStrut(6));

        JTextField textField =
                new JTextField(parameter.getInitialValue());
        textField.setPreferredSize(new Dimension(200, 20));
        textField.setMinimumSize(new Dimension(200, 20));
        textField.setMaximumSize(new Dimension(200, 20));
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(textField);

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

    private static void setupPanel(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private static void setupNameLabel(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

    }
}
