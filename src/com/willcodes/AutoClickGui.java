package com.willcodes;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.renderable.RenderableImageProducer;
import java.util.concurrent.TimeUnit;

public class AutoClickGui {

    private static final int sliderMin = 0;
    private static final int sliderMax = 20;
    private static final int sliderInit = 10;
    private int rate = 0;
    private int rateInMs = rate * 1000;
    private boolean on = false;

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);
    private final JPanel panel = new JPanel();
    private final JFrame frame = new JFrame("Wills auto clicker");
    private final JButton startAuto = new JButton("Start or click s");
    private final JButton stopAuto = new JButton("Stop or click o");

    public AutoClickGui() throws AWTException, InterruptedException {
        InitializeUI();
    }

    public void InitializeUI() throws InterruptedException {
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        fonts();
        sliderListener();
        panel.add(slider);
        panel.add(startAuto);
        panel.add(stopAuto);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void fonts() {
        slider.setFont(new Font("Times new Roman", Font.ITALIC, 13));
        frame.setFont(new Font("Times new Roman", Font.BOLD, 18));
        startAuto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        stopAuto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
    }

    public void sliderListener() {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rate = slider.getValue();
            }
        });
    }

    public void buttonListeners() {
        startAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Robot robot = new Robot();
                            Thread.sleep(rateInMs);
                            while (on) {
                                robot.mousePress(InputEvent.BUTTON1_MASK);
                                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                            }

                        } catch (AWTException | InterruptedException awtException) {
                            awtException.printStackTrace();
                        }
                    }
                }).start();

            }
        });


        stopAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                on = false;
            }
        });
    }


    public static void main(String[] args) throws AWTException, InterruptedException {
        new AutoClickGui();
    }
}
