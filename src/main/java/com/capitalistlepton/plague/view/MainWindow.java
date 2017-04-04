package com.capitalistlepton.plague.view;

import com.capitalistlepton.plague.Plague;
import com.capitalistlepton.plague.model.Antibiotic;

import javax.swing.*;
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
    private static final Color BACKGROUND = new Color(0x404446);

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

        Container pane = this.getContentPane();
        pane.setBackground(BACKGROUND);
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
        stats.setBackground(BACKGROUND);
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
        controlBar.setBackground(BACKGROUND);
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

        this.setVisible(true);
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
