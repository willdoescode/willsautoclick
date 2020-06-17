package com.willcodes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class AutoClickGui {

    private static final int sliderMin = 0;
    private static final int sliderMax = 20;
    private static final int sliderInit = 10;
    private int rate = 10;
    private boolean clickerActive = false;
    private boolean threadStarted = false;

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);
    private final JPanel panel = new JPanel();
    private final JFrame frame = new JFrame("Wills auto clicker");
    private final JButton startAuto = new JButton("Start or click s");
    private final JButton stopAuto = new JButton("Stop or click o");
    private final Robot robot = new Robot();


    public AutoClickGui() throws AWTException, InterruptedException {
        InitializeUI();
    }

    public void sliderListener() {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rate = slider.getValue();
                System.out.println("Rate in seconds: " + rate);
                System.out.println("Rate in ms: " + rate * 1000);
            }
        });
    }

    public void InitializeUI() throws InterruptedException {
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        fonts();
        sliderListener();
        startAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAuto.setEnabled(false);
                stopAuto.setEnabled(true);
                clickerActive = true;
                Thread clickclick = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        threadStarted = true;
                        while (clickerActive) {
                            try {
                                click();
                                TimeUnit.SECONDS.sleep(rate);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        threadStarted = false;
                        }
                    });
                if (!threadStarted) {
                       clickclick.start();
                }
            }
        });

        stopAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickerActive = false;
                startAuto.setEnabled(true);
                stopAuto.setEnabled(false);
            }
        });
        panel.add(slider);
        panel.add(startAuto);
        panel.add(stopAuto);
        frame.add(panel);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void fonts() {
        slider.setFont(new Font("Times new Roman", Font.ITALIC, 13));
        frame.setFont(new Font("Times new Roman", Font.BOLD, 18));
        startAuto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        stopAuto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
    }




    public void click() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = b.x;
        int y = b.y;
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    public static void main(String[] args) throws AWTException, InterruptedException {
        new AutoClickGui();
    }
}
