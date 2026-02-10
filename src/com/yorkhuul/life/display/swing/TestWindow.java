package com.yorkhuul.life.display.swing;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class TestWindow extends JFrame {

    public TestWindow() {
        super("Map");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
    }

    public void mainMenu() {
        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JButton("Create a new map"));
        contentPane.add(new JButton("Load an existing map"));
    }


    public static void main(String[] args) {
        // apply look
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        }
        catch(UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }

        // start the main window
        TestWindow main = new TestWindow();
        main.setVisible(true);
        main.mainMenu();
    }
}
