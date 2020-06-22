package com.willcodes;

import javax.swing.*;
import java.awt.*;

public class InitGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Frame();
            } catch (AWTException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
