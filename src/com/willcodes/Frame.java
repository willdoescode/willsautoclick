package com.willcodes;

import javax.swing.*;
import com.apple.eawt.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

public class Frame extends JFrame {
    public Frame() throws AWTException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
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

        Application myApp = Application.getApplication();


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
        myApp.setDefaultMenuBar(menuBar);
        Listen();
        setTitle("Wills auto clicker");
        setContentPane(new Panel(socials, otherProjects, github, twitter, instagram, false));
        setAlwaysOnTop(true);
        setMinimumSize(new Dimension(212, 162));
        setPreferredSize(new Dimension(212, 162));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        Application.getApplication().setDockIconImage(new ImageIcon(getClass().getResource("icons/computer.png")).getImage());
        setIconImage(new ImageIcon(getClass().getResource("icons/computer.png")).getImage());

    }

    private void Listen() {
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
