package com.capitalistlepton.view;

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

    private JPanel bacteria;
    private JPanel stats;
    private JPanel controlBar;

    public MainWindow(BacteriaController con, boolean fullscreen) {
        if (fullscreen) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
        } else {
            this.setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        Container pane = this.getContentPane();
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
        stats.setBackground(new Color(0x404446));
        c.weightx = 0.2;
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(stats, c);

        controlBar = new JPanel();
        controlBar.setBackground(new Color(0xff0000));
        c.weightx = 0.8;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(controlBar, c);

        this.setVisible(true);
    }

    @Override
    public void repaint() {
        super.repaint();
        bacteria.repaint();
    }
}
