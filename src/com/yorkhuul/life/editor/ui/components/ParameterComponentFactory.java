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

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 6, 5, 15)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Name label (row 0) =====
        JLabel nameLabel = new JLabel(parameter.getName(), SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(nameLabel, gbc);

        // ===== Slider (row 1, col 0) =====
        JSlider slider = new JSlider(
                parameter.getMin(),
                parameter.getMax(),
                parameter.getInitial()
        );

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;  // slider prend tout l’espace
        panel.add(slider, gbc);

        // ===== Value label (row 1, col 1) =====
        JLabel valueLabel = new JLabel(
                parameter.formatValue(parameter.getInitial()),
                SwingConstants.RIGHT
        );
        valueLabel.setPreferredSize(new Dimension(40, 20));

        gbc.gridx = 1;
        gbc.weightx = 0; // ne s'étire PAS
        panel.add(valueLabel, gbc);

        // ===== Update dynamique =====
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            parameter.updateFromSlider(value);
            valueLabel.setText(parameter.formatValue(value));
        });

        return panel;
    }

    private static JComponent createTextField(StringParameter parameter) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 6, 5, 15)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Name label =====
        JLabel nameLabel = new JLabel(parameter.getName(), SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(nameLabel, gbc);

        // ===== TextField =====
        JTextField textField = new JTextField(parameter.getInitialValue());
        textField.setPreferredSize(
                new Dimension(textField.getPreferredSize().width, 22)
        );


        gbc.gridy = 1;
        gbc.weightx = 1.0; // prend toute la largeur
        panel.add(textField, gbc);

        // ===== Update dynamique =====
        textField.getDocument().addDocumentListener(new DocumentListener() {

            private void update() {
                parameter.updateFromText(textField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) { update(); }

            @Override
            public void removeUpdate(DocumentEvent e) { update(); }

            @Override
            public void changedUpdate(DocumentEvent e) { update(); }
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
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                panel.getPreferredSize().height));
    }

    private static void setupNameLabel(JLabel label) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

    }
}
