package com.capitalistlepton;

import com.capitalistlepton.model.Antibiotic;
import com.capitalistlepton.model.BacteriaController;
import com.capitalistlepton.view.AntibioticCheckBox;
import com.capitalistlepton.view.MainWindow;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class Plague implements ItemListener, PlagueConstants {

    private ArrayList<Antibiotic> activeAntibiotics;
    private int funds;
    private BacteriaController con;

    private static BacteriaController controller;
    private static Plague p;
    private static MainWindow window;
    private static boolean interrupt;

    public static void main(String[] args) {
        while (true)
            runGame();
    }

    public static void runGame() {
        controller = new BacteriaController(STARTING_BACTERIA, STARTING_GENOTYPE);
        p = new Plague(controller, STARTING_FUNDS);
        if (window == null) {
            window = new MainWindow(controller, p, FULL_SCREEN);
        } else {
            window.reset(controller, p);
//            window.dispose();
//            window = new MainWindow(null,null, false);
        }
        interrupt = false;
        try {
            while (p.turn() && !interrupt) {
                window.repaint();
                sleep(WAIT_BETWEEN_TURNS);
            }
            // Display the final scene where the game is either won or lost.
            window.repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (p.won()) {
            window.displayMessage("Won!");
        } else {
            window.displayMessage("Lost :(");
        }
    }

    public Plague(BacteriaController con, int funds) {
        this.activeAntibiotics = new ArrayList<Antibiotic>();
        this.funds = funds;
        this.con = con;
    }

    public boolean turn() {
        if (con.bacteriaCount() < MAX_BACTERIA && con.bacteriaCount() > 0 && funds > 0) {
            con.replicate();
            for (Antibiotic activeAntibiotic : activeAntibiotics) {
                con.useAntibiotic(activeAntibiotic);
                funds -= activeAntibiotic.cost();
            }
        }
        return con.bacteriaCount() < MAX_BACTERIA && con.bacteriaCount() > 0 && funds > 0;
    }

    public int funds() {
        return funds;
    }

    public void itemStateChanged(ItemEvent e) {
        AntibioticCheckBox check = (AntibioticCheckBox) e.getItemSelectable();
        if (check.isSelected()) {
            activeAntibiotics.add(check.getAntibiotic());
        } else {
            activeAntibiotics.remove(check.getAntibiotic());
        }
    }

    public boolean won() {
        return con.bacteriaCount() == 0;
    }

    public static class RestartPlague extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            interrupt = true;
            runGame();
        }
    }

//    @Override
//    public void keyTyped(KeyEvent e) {}
//
//    @Override
//    public void keyPressed(KeyEvent e) {}
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        switch (e.getKeyChar()) {
//            case 'q': System.exit(0);
//            case 'r': {
//                this.con = new BacteriaController(STARTING_BACTERIA, STARTING_GENOTYPE);
//                this.funds = STARTING_FUNDS;
//                this.activeAntibiotics.clear();
//            };
//        }
//    }
}
