package com.willcodes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

public class Panel extends JPanel {
    public Panel(JMenu socials, JMenu otherProjects, JMenuItem github, JMenuItem twitter, JMenuItem instagram) throws AWTException {
        InitPanel(socials, otherProjects, github, twitter, instagram);
    }
    private static final int sliderMin = 0;
    private static final int sliderMax = 30;
    private static final int sliderInit = 15;
    private int rate = 15;
    private boolean clickerActive = false;
    private boolean threadStarted = false;
    private final Robot robot = new Robot();
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);
    private final JPanel panel = new JPanel(new FlowLayout());
    private final JFrame frame = new JFrame("Wills auto clicker");
    private final JButton startAuto = new JButton("Start");
    private final JButton stopAuto = new JButton("Stop");
    private final JTextField rateInMs = new JTextField("Clicks every: " + rate + "ms", 11);

    public void sliderListener() {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rate = slider.getValue();
                if (rate == 0) {
                    rate = 1;
                }
                rateInMs.setText("Clicks every : " + rate + "ms");
            }
        });
    }

    private void InitPanel(JMenu socials, JMenu otherProjects, JMenuItem github, JMenuItem twitter, JMenuItem instagram) {
        sliderListener();
        startAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                socials.setEnabled(false);
                otherProjects.setEnabled(false);
                startAuto.setEnabled(false);
                stopAuto.setEnabled(true);
                clickerActive = true;
                slider.setEnabled(false);
                Thread clickclick = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        threadStarted = true;
                        while (clickerActive) {
                            try {
                                click();
                                if (rate == 0) {
                                    rate = 1;
                                }
                                TimeUnit.MILLISECONDS.sleep(rate);
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
                socials.setEnabled(true);
                otherProjects.setEnabled(true);
                clickerActive = false;
                startAuto.setEnabled(true);
                stopAuto.setEnabled(false);
                slider.setEnabled(true);
            }
        });

        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setInverted(true);
        slider.setToolTipText("Change the click delay in ms");
        rateInMs.setToolTipText("Shows the click intervals");
        startAuto.setToolTipText("Start the auto clicker");
        startAuto.setBackground(Color.GREEN);
        startAuto.setOpaque(true);
        stopAuto.setToolTipText("Stop the auto clicker");
        stopAuto.setBackground(Color.RED);
        stopAuto.setOpaque(true);
        rateInMs.setEditable(false);
        add(rateInMs);
        add(slider);
        add(startAuto);
        add(stopAuto);
        fonts(github, twitter, instagram, socials, otherProjects);
    }

    public void fonts(JMenuItem github, JMenuItem twitter, JMenuItem instagram, JMenu socials, JMenu otherProjects) {
        slider.setFont(new Font("Times new Roman", Font.ITALIC, 13));
        startAuto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        stopAuto.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        rateInMs.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        socials.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        otherProjects.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        github.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        twitter.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        instagram.setFont(new Font("Times new Roman", Font.PLAIN, 14));
    }



    private void click() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
