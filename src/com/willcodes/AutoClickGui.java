package com.willcodes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class AutoClickGui {

    private static final int sliderMin = 0;
    private static final int sliderMax = 30;
    private static final int sliderInit = 15;
    private int rate = 15;
    private boolean clickerActive = false;
    private boolean threadStarted = false;

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);
    private final JPanel panel = new JPanel(new FlowLayout());
    private final JFrame frame = new JFrame("Wills auto clicker");
    private final JButton startAuto = new JButton("Start");
    private final JButton stopAuto = new JButton("Stop");
    private final JTextField rateInMs = new JTextField("Clicks every: " + rate + "ms", 11);
    private final Robot robot = new Robot();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu about = new JMenu("About");
    private final JMenu socials = new JMenu("Socials");
    private final JMenuItem twitter = new JMenuItem("Twitter");
    private final JMenuItem instagram = new JMenuItem("Insta");
    private final JMenu otherProjects = new JMenu("Projects");
    private final JMenuItem github = new JMenuItem("github");


    public AutoClickGui() throws AWTException {
        InitializeUI();
    }

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

    public void InitializeUI() {
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setInverted(true);
        fonts();
        sliderListener();
        startAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                clickerActive = false;
                startAuto.setEnabled(true);
                stopAuto.setEnabled(false);
                slider.setEnabled(true);
            }
        });


        twitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://twitter.com/williamisahuman");
                    openPage(uri);
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });

        instagram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://instagram.com/will1amlane");
                    openPage(uri);
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });

        github.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://github.com/pietales");
                    openPage(uri);
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });

        menuBar.setBorderPainted(false);
        socials.setBackground(Color.DARK_GRAY);
        otherProjects.add(github);
        slider.setToolTipText("Change the click delay in ms");
        socials.add(twitter);
        socials.add(instagram);
        socials.setToolTipText("My social media accounts");
        instagram.setToolTipText("My insta");
        twitter.setToolTipText("My twitter");
        otherProjects.setToolTipText("My other coding projects");
        github.setToolTipText("My github");
        menuBar.add(socials);
        menuBar.add(otherProjects);
        rateInMs.setToolTipText("Shows the click intervals");
        startAuto.setToolTipText("Start the auto clicker");
        startAuto.setBackground(Color.GREEN);
        startAuto.setOpaque(true);
        stopAuto.setToolTipText("Stop the auto clicker");
        stopAuto.setBackground(Color.RED);
        stopAuto.setOpaque(true);
        rateInMs.setEditable(false);
        JFrame.setDefaultLookAndFeelDecorated(true);
        panel.add(rateInMs);
        panel.add(slider);
        panel.add(startAuto);
        panel.add(stopAuto);
        frame.setJMenuBar(menuBar);
        frame.setContentPane(panel);
        frame.setAlwaysOnTop(true);
        frame.setPreferredSize(new Dimension(212, 186));
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
        rateInMs.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        socials.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        otherProjects.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        github.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        twitter.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        instagram.setFont(new Font("Times new Roman", Font.PLAIN, 14));
    }


    public void click() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void openPage(URI url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(url);
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws AWTException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new AutoClickGui();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
