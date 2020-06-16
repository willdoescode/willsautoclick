package com.willcodes;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.renderable.RenderableImageProducer;

public class AutoClickGui {

    private static final int sliderMin = 0;
    private static final int sliderMax = 20;
    private static final int sliderInit = 10;
    private int position = 0;

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);
    private final JPanel panel = new JPanel();
    private final JFrame frame = new JFrame("Wills auto clicker");

    public AutoClickGui() {
        InitializeUI();
    }

    public void InitializeUI() {
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        fonts();
        sliderListener();
        panel.add(slider);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void fonts() {
        slider.setFont(new Font("Times new Roman", Font.ITALIC, 13));
        frame.setFont(new Font("Times new Roman", Font.BOLD, 18));
    }

    public void sliderListener() {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                System.out.println(value);
            }
        });
    }


    public static void main(String[] args) {
        new AutoClickGui();
    }
}
