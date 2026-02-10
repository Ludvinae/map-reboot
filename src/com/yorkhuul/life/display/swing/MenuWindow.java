package com.yorkhuul.life.display.swing;

import javax.swing.*;

public class MenuWindow extends JDialog {
    private JPanel contentPane;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JButton newButton;
    private JButton loadButton;
    private JButton buttonOK;

    public MenuWindow() {
        setContentPane(contentPane);
        setModal(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        MenuWindow dialog = new MenuWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
