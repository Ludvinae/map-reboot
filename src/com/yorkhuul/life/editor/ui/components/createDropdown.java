package com.yorkhuul.life.editor.ui.components;

import com.yorkhuul.life.core.engine.parameters.EnumParameter;

import javax.swing.*;
import java.awt.*;

private static <T extends Enum<T>> JComponent createDropdown(EnumParameter<?> parameter) {

    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
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

    // ===== ComboBox =====
    JComboBox comboBox = new JComboBox(parameter.getValues());
    comboBox.setSelectedItem(parameter.getValue());

    gbc.gridy = 1;
    gbc.weightx = 1.0;
    panel.add(comboBox, gbc);

    comboBox.addActionListener(e -> {
        T selected = (T) comboBox.getSelectedItem();
        parameter.updateFromSelection(selected);
    });

    return panel;
}
