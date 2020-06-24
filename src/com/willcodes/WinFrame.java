package com.willcodes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;


public class WinFrame extends JFrame {
    public WinFrame() throws AWTException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        InitFrame();
    }

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu about = new JMenu("About");
    public final JMenu socials = new JMenu("Socials");
    private final JMenuItem twitter = new JMenuItem("Twitter");
    private final JMenuItem instagram = new JMenuItem("Insta");
    public final JMenu otherProjects = new JMenu("Projects");
    private final JMenuItem github = new JMenuItem("github");

    private void InitFrame() throws AWTException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        otherProjects.add(github);
        menuBar.setBorderPainted(false);
        socials.setBackground(Color.DARK_GRAY);
        socials.add(twitter);
        socials.add(instagram);
        socials.setToolTipText("My social media accounts");
        instagram.setToolTipText("My insta");
        twitter.setToolTipText("My twitter");
        otherProjects.setToolTipText("My other coding projects");
        github.setToolTipText("My github");
        menuBar.add(socials);
        menuBar.add(otherProjects);
        setJMenuBar(menuBar);
        Listen();
        setTitle("Wills auto clicker");
        setContentPane(new Panel(socials, otherProjects, github, twitter, instagram, true));
        setAlwaysOnTop(true);
        setPreferredSize(new Dimension(212, 182));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);;
        setIconImage(new ImageIcon(getClass().getResource("icons/computer.png")).getImage());

    }

    private void Listen() {
        twitter.addActionListener(e -> {
            try {
                URI uri = new URI("https://twitter.com/williamisahuman");
                openPage(uri);
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });

        instagram.addActionListener(e -> {
            try {
                URI uri = new URI("https://instagram.com/will1amlane");
                openPage(uri);
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
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
    }



    private void openPage(URI url) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(url);
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }
}
