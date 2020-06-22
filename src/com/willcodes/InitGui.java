package com.willcodes;

import javax.swing.*;
import java.awt.*;

public class InitGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Frame();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
