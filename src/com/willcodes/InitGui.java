package com.willcodes;

import javax.swing.*;
import java.awt.*;

public class InitGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Hello World!");
                new Frame();
            } catch (AWTException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
