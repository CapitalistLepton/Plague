package com.capitalistlepton.plague.view;

import com.capitalistlepton.plague.PlagueConstants;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by zanelittrell on 4/3/17.
 */
public class SplashPanel extends JPanel {

    private SplashPanelState state;
    private JComponent[] logoComponents;
    private JComponent[] tutorialComponents;
    private JComponent[] scoreComponents;

    public SplashPanel() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        JLabel logo = new JLabel("Plague");
        logo.setFont(PlagueConstants.LOGO_FONT);
        add(logo);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, logo, -100, SpringLayout.VERTICAL_CENTER, this);
        JLabel version = new JLabel("v0.0.1");
        version.setFont(PlagueConstants.BIG_GAME_FONT);
        add(version);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, version, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, version, 0, SpringLayout.VERTICAL_CENTER, this);
        logoComponents = new JComponent[] {logo, version};

        JLabel help = new JLabel("Help:");
        help.setFont(PlagueConstants.BIG_GAME_FONT);
        help.setVisible(false);
        add(help);
        layout.putConstraint(SpringLayout.NORTH, help, 30, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, help, 30, SpringLayout.WEST, this);
        JTextArea tutorial = new JTextArea();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream file = classLoader.getResourceAsStream("help.txt");
        Scanner reader = new Scanner(file);
        String text = reader.nextLine();
        while (reader.hasNextLine()) {
            tutorial.append(text + "\n");
            text = reader.nextLine();
        }
        tutorial.append(text); // Append the last text that was read off.
        tutorial.setFont(PlagueConstants.GAME_FONT);
        tutorial.setBackground(PlagueConstants.LIGHT);
        tutorial.setLineWrap(true);
        tutorial.setWrapStyleWord(true);
        tutorial.setEditable(false);
        tutorial.setVisible(false);
        add(tutorial);
        layout.putConstraint(SpringLayout.NORTH, tutorial, 80, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tutorial, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, tutorial, -30, SpringLayout.EAST, this);
        tutorialComponents = new JComponent[] {help, tutorial};

        JLabel scores = new JLabel("High Scores:");
        scores.setFont(PlagueConstants.BIG_GAME_FONT);
        scores.setVisible(false);
        add(scores);
        layout.putConstraint(SpringLayout.NORTH, scores, 30, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, scores, 30, SpringLayout.WEST, this);
        scoreComponents = new JComponent[] {scores};

        state = SplashPanelState.LOGO;
    }

    public void setState(SplashPanelState state) {
        switch (this.state) {
            case LOGO: showComponents(logoComponents, false); break;
            case TUTORIAL: showComponents(tutorialComponents, false); break;
            case SCORES: showComponents(scoreComponents, false); break;
        }
        this.state = state;
        switch (state) {
            case LOGO: showComponents(logoComponents, true); break;
            case TUTORIAL: showComponents(tutorialComponents, true); break;
            case SCORES: showComponents(scoreComponents, true); break;
        }
    }

    private void showComponents(JComponent[] comps, boolean visible) {
        for (int i = 0; i < comps.length; i++) {
            comps[i].setVisible(visible);
        }
    }
}
