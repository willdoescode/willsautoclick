package com.willcodes;

import javax.swing.*;
import java.awt.*;

public class InitGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                if (System.getProperty("os.name").equals("Mac OS X")) {
                    new Frame();
                } else {
                    new WinFrame();
                }
            } catch (AWTException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
