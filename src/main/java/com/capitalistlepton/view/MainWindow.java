package com.capitalistlepton.view;

import com.capitalistlepton.Plague;
import com.capitalistlepton.model.Antibiotic;
import com.capitalistlepton.model.BacteriaController;

import javax.swing.*;
import java.awt.*;

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
    private JPanel stats;
    private JPanel controlBar;

    public MainWindow(BacteriaController con, Plague instance, boolean fullscreen) {
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


        bacteria = new BacteriaPanel(con);
        bacteria.setBackground(new Color(0x202020));
        c.weightx = 0.8;
        c.weighty = 0.9;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(bacteria, c);

        stats = new StatsPanel(con);
        stats.setBackground(BACKGROUND);
        c.weightx = 0.2;
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(stats, c);

        controlBar = new JPanel();
        for (Antibiotic a : Antibiotic.values()) {
            AntibioticCheckBox check = new AntibioticCheckBox(a, instance);
            controlBar.add(check);
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

        this.setVisible(true);
    }

    @Override
    public void repaint() {
        super.repaint();
        bacteria.repaint();
    }

    public void displayMessage(String message) {
        bacteria.writeMessage(message);
    }
}
