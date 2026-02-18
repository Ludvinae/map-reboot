package com.yorkhuul.life.editor.ui.components;

import com.yorkhuul.life.core.engine.parameters.*;

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
        if (parameter instanceof EnumParameter<?> enumParameter) {
            return createDropdownUnsafe(enumParameter);
        }
        if (parameter instanceof SliderParameter<?> sliderParameter) {
            return createSliderUnsafe(sliderParameter);
        }
        throw new IllegalArgumentException("Unsupported parameter type");
    }

    @SuppressWarnings("unhecked")
    private static <T> JComponent createSliderUnsafe(SliderParameter<T> rawParam) {
        return createSlider((SliderParameter<T>) rawParam);
    }

    private static <T> JComponent createSlider(SliderParameter<T> parameter) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 15, 3, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Name =====
        JLabel nameLabel = new JLabel(parameter.getName(), SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(nameLabel, gbc);

        // ===== Slider =====
        JSlider slider = new JSlider(
                parameter.getMin(),
                parameter.getMax(),
                parameter.toSlider(parameter.getValue())
        );

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        panel.add(slider, gbc);

        // ===== Value label =====
        JLabel valueLabel = new JLabel(
                parameter.format(parameter.getValue()),
                SwingConstants.RIGHT
        );
        valueLabel.setPreferredSize(new Dimension(40, 20));

        gbc.gridx = 1;
        gbc.weightx = 0;
        panel.add(valueLabel, gbc);

        // ===== Listener =====
        slider.addChangeListener(e -> {

            int raw = slider.getValue();

            Object converted = parameter.fromSlider(raw);

            // cast sûr car on vient du paramètre
            @SuppressWarnings("unchecked")
            SliderParameter<Object> p =
                    (SliderParameter<Object>) parameter;

            p.setValue(converted);

            valueLabel.setText(parameter.format(parameter.getValue()));
        });

        return panel;
    }

    private static JComponent createTextField(StringParameter parameter) {
        /*
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 15, 3, 15)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Name label =====
        JLabel nameLabel = new JLabel(parameter.getName(), SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(nameLabel, gbc);

        // ===== TextField =====
        JTextField textField = new JTextField(parameter.getValue());
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 22));

        gbc.gridy = 1;
        gbc.weightx = 1.0; // prend toute la largeur
        panel.add(textField, gbc);

         */

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 15, 3, 15)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // ===== Name label =====
        JLabel nameLabel = new JLabel(parameter.getName(), SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(nameLabel, gbc);

        // ===== TextField =====
        JTextField textField = new JTextField(parameter.getValue());

        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 22));

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(textField, gbc);

        // ===== Update dynamique =====
        textField.getDocument().addDocumentListener(new DocumentListener() {

            private void update() {
                parameter.setValue(textField.getText());
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

        JCheckBox checkBox = new JCheckBox(parameter.getName(), parameter.getValue());

        checkBox.addActionListener(e ->
                parameter.setValue(checkBox.isSelected())
        );
        return checkBox;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> JComponent createDropdownUnsafe(EnumParameter<T> rawParam) {
        return createDropdown((EnumParameter<T>) rawParam);
    }

    private static <T extends Enum<T>>
    JComponent createDropdown(EnumParameter<T> parameter) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(3, 15, 3, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 0, 2, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Name =====
        JLabel nameLabel = new JLabel(parameter.getName(), SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(nameLabel, gbc);

        // ===== ComboBox =====
        JComboBox<T> comboBox = new JComboBox<>(parameter.getValues());
        comboBox.setSelectedItem(parameter.getValue());

        gbc.gridy = 1;
        panel.add(comboBox, gbc);

        comboBox.addActionListener(e ->
                parameter.setValue((T) comboBox.getSelectedItem())
        );

        return panel;
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
