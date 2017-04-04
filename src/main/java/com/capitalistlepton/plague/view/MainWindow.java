package com.capitalistlepton.plague.view;

import com.capitalistlepton.plague.Plague;
import com.capitalistlepton.plague.PlagueConstants;
import com.capitalistlepton.plague.model.Antibiotic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class MainWindow extends JFrame {

    private static final int WINDOW_X = 200;
    private static final int WINDOW_Y = 100;
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 600;

    private BacteriaPanel bacteria;
    private StatsPanel stats;
    private JPanel controlBar;
    private ArrayList<AntibioticCheckBox> boxes;

    public MainWindow(Plague instance, boolean fullscreen) {
        if (fullscreen) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
        } else {
            this.setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }


        Container pane = this.getContentPane();
        pane.setBackground(PlagueConstants.BACKGROUND);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        SplashPanel splash = new SplashPanel();
        splash.setBackground(PlagueConstants.LIGHT);
        c.weightx = 0.9;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(splash, c);

        JPanel menu = new JPanel(new GridLayout(4,1, 0, 0));
        menu.setBackground(PlagueConstants.BACKGROUND);

        JButton play = new JButton();
        play.setOpaque(true);
        play.setFocusPainted(false);
        play.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        play.setBackground(PlagueConstants.DARK);
        play.setForeground(Color.WHITE);
        play.setFont(PlagueConstants.BIG_GAME_FONT);
        play.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                drawMainContent(instance);
                revalidate();
                instance.thread.start();
            }
        });
        play.setText("Play");
        menu.add(play);

        JButton help = new JButton();
        help.setOpaque(true);
        help.setFocusPainted(false);
        help.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        help.setBackground(PlagueConstants.DARK);
        help.setForeground(Color.WHITE);
        help.setFont(PlagueConstants.BIG_GAME_FONT);
        help.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splash.setState(SplashPanelState.TUTORIAL);
            }
        });
        help.setText("Help");
        menu.add(help);

        JButton scores = new JButton();
        scores.setOpaque(true);
        scores.setFocusPainted(false);
        scores.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scores.setBackground(PlagueConstants.DARK);
        scores.setForeground(Color.WHITE);
        scores.setFont(PlagueConstants.BIG_GAME_FONT);
        scores.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splash.setState(SplashPanelState.SCORES);
            }
        });
        scores.setText("High Scores");
        menu.add(scores);

        c.weightx = 0.1;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(menu, c);

        this.setVisible(true);
    }

    private void drawMainContent(Plague instance) {
        Container pane = this.getContentPane();
        pane.setBackground(PlagueConstants.BACKGROUND);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        bacteria = new BacteriaPanel(instance);
        bacteria.setBackground(new Color(0x202020));
        c.weightx = 0.8;
        c.weighty = 0.9;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(bacteria, c);

        stats = new StatsPanel(instance);
        stats.setBackground(PlagueConstants.BACKGROUND);
        c.weightx = 0.2;
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(stats, c);

        controlBar = new JPanel();
        boxes = new ArrayList<AntibioticCheckBox>(Antibiotic.values().length);
        for (Antibiotic a : Antibiotic.values()) {
            AntibioticCheckBox check = new AntibioticCheckBox(a, instance);
            check.setEnabled(false);
            controlBar.add(check);
            boxes.add(check);
        }
        controlBar.setBackground(PlagueConstants.BACKGROUND);
        c.weightx = 0.8;
        c.weighty = 0.01;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(controlBar, c);

        JPanel money = new FundsPanel(instance);
        money.setBackground(Color.BLACK);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(money, c);

        // Add key bindings
        controlBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"),
                "restart");
        controlBar.getActionMap().put("restart",
                new Plague.RestartPlague());
        controlBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"),
                "quit");
        controlBar.getActionMap().put("quit",
                new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
        for (int i = 0; i < boxes.size(); i++) {
            if (i >= 0 && i <= 9) {
                controlBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("" + (i + 1)),
                        "select" + (i + 1));
                int checkBoxIndex = i;
                controlBar.getActionMap().put("select" + (i + 1),
                        new AbstractAction() {
                            public void actionPerformed(ActionEvent e) {
                                selectCheckBox(checkBoxIndex);
                            }
                        });
            } else {
                System.err.println("Too many antibiotics for key bindings.");
            }
        }
    }

    private void selectCheckBox(int index) {
        if (boxes.get(index).isEnabled()) {
            boxes.get(index).setSelected(!boxes.get(index).isSelected());
        }
    }

    public void enableCheckBoxes(boolean enable) {
        for (AntibioticCheckBox check : boxes) {
            check.setEnabled(enable);
        }
    }

    public void displayMessage(String message) { bacteria.writeMessage(message); }

    public void resetBacteriaPanel() { bacteria.clearMessage(); }
}
