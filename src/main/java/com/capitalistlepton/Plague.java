package com.capitalistlepton;

import com.capitalistlepton.model.Antibiotic;
import com.capitalistlepton.model.Bacterium;
import com.capitalistlepton.view.AntibioticCheckBox;
import com.capitalistlepton.view.MainWindow;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class Plague implements ItemListener {

    private ArrayList<Antibiotic> activeAntibiotics;
    private ArrayList<Bacterium> bacteria;
    private Random rand;
    private int funds;
//    private BacteriaModel bac;

//    private static BacteriaModel bacteria;
    private static Plague p;
    private static MainWindow window;
    private static boolean interrupt;

    public static void main(String[] args) {
//        bacteria = new BacteriaModel(PlagueConstants.STARTING_BACTERIA, PlagueConstants.STARTING_GENOTYPE);
        p = new Plague(PlagueConstants.STARTING_FUNDS);
        window = new MainWindow(p, PlagueConstants.FULL_SCREEN);
        while (p.turn()) {
            window.repaint();
            try {
                sleep(PlagueConstants.WAIT_BETWEEN_TURNS);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        if (p.won()) {
            window.displayMessage("Won!");
        } else {
            window.displayMessage("Lost :(");
        }
    }

//    public static void runGame() {
//        bacteria = new BacteriaModel(STARTING_BACTERIA, STARTING_GENOTYPE);
//        p = new Plague(bacteria, STARTING_FUNDS);
//        if (window == null) {
//            window = new MainWindow(bacteria, p, FULL_SCREEN);
//        } else {
//            window.reset(bacteria, p);
////            window.dispose();
////            window = new MainWindow(null,null, false);
//        }
//        interrupt = false;
//        try {
//            while (p.turn() && !interrupt) {
//                window.repaint();
//                sleep(WAIT_BETWEEN_TURNS);
//            }
//            // Display the final scene where the game is either won or lost.
//            window.repaint();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (p.won()) {
//            window.displayMessage("Won!");
//        } else {
//            window.displayMessage("Lost :(");
//        }
//    }

    public Plague(int funds) {
        this.activeAntibiotics = new ArrayList<Antibiotic>();
        this.funds = funds;
        this.bacteria = new ArrayList<Bacterium>(PlagueConstants.STARTING_BACTERIA);
        this.rand = new Random();
        for (int i = 0; i < PlagueConstants.STARTING_BACTERIA; i++) {
            bacteria.add(new Bacterium(PlagueConstants.STARTING_GENOTYPE,
                    rand.nextInt(PlagueConstants.X_RESOLUTION),
                    rand.nextInt(PlagueConstants.Y_RESOLUTION)));
        }
    }

    public boolean turn() {
        if (bacteriaCount() < PlagueConstants.MAX_BACTERIA && bacteriaCount() > 0 && funds > 0) {
            replicate();
            for (Antibiotic activeAntibiotic : activeAntibiotics) {
                useAntibiotic(activeAntibiotic);
                funds -= activeAntibiotic.cost();
            }
        }
        return bacteriaCount() < PlagueConstants.MAX_BACTERIA && bacteriaCount() > 0 && funds > 0;
    }

    public int funds() {
        return funds;
    }

    // BacteriaModel functions

    public ArrayList<Bacterium> getBacteriaList() {
        return bacteria;
    }

    public int bacteriaCount() {
        return bacteria.size();
    }

    public void replicate() {
        for (int i = 0; i < bacteria.size(); i++) {
            // Replicate 10% chance for each bacterium
            if(rand.nextFloat() < 0.1f) {
                Bacterium b = bacteria.get(i);
                Bacterium ba = new Bacterium(b.getGenotype(),
                        rand.nextInt(PlagueConstants.X_RESOLUTION),
                        rand.nextInt(PlagueConstants.Y_RESOLUTION));
                bacteria.add(ba);
                i--;
            }
        }
    }

    public void useAntibiotic(Antibiotic a) {
        for (int i = 0; i < bacteria.size(); i++) {
            if (rand.nextFloat() < a.effectiveness()
                    && a.targetGenotype().matcher(bacteria.get(i).getGenotype()).matches()) {
                bacteria.remove(i);
                i--;
            }
        }
    }

    public Map<String, Integer> getGenotypes() {
        Map<String, Integer> genotypes = new HashMap<String, Integer>();
        for (Bacterium b : bacteria) {
            String g = b.getGenotype();
            if (genotypes.containsKey(g)) {
                genotypes.put(g, genotypes.get(g) + 1);
            } else {
                genotypes.put(g, 1);
            }
        }
        return genotypes;
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
        return bacteriaCount() == 0;
    }

    public void restart() {
        this.bacteria = new ArrayList<Bacterium>(PlagueConstants.STARTING_BACTERIA);
        for (int i = 0; i < PlagueConstants.STARTING_BACTERIA; i++) {
            bacteria.add(new Bacterium(PlagueConstants.STARTING_GENOTYPE,
                    rand.nextInt(PlagueConstants.X_RESOLUTION),
                    rand.nextInt(PlagueConstants.Y_RESOLUTION)));
        }
        this.funds = PlagueConstants.STARTING_FUNDS;
    }

    public static class RestartPlague extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            p.restart();
        }
    }

//    public class PlagueModel implements Runnable {
//
//        @Override
//        public void run() {
//
//        }
//    }

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
//                this.bac = new BacteriaModel(STARTING_BACTERIA, STARTING_GENOTYPE);
//                this.funds = STARTING_FUNDS;
//                this.activeAntibiotics.clear();
//            };
//        }
//    }
}
