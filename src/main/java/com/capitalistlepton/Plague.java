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

    private static Plague instance;
    private static MainWindow window;
    private static boolean kill;

    private static volatile Thread thread;

    public static void main(String[] args) {
        instance = new Plague(PlagueConstants.STARTING_FUNDS);
        window = new MainWindow(instance, PlagueConstants.FULL_SCREEN);
        thread = new Thread(() -> runGame());
        thread.start();
    }

    private static void runGame() {
        while (!kill && instance.turn()) {
            window.repaint();
            try {
                sleep(PlagueConstants.WAIT_BETWEEN_TURNS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (kill) {
            kill = false;
        } else {
            if (instance.won()) {
                window.displayMessage("Won!");
            } else {
                window.displayMessage("Lost :(");
            }
        }
    }


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
            if (rand.nextFloat() < 0.1f) {
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

    // End BacteriaModel

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
        kill = true;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.bacteria = new ArrayList<Bacterium>(PlagueConstants.STARTING_BACTERIA);
        for (int i = 0; i < PlagueConstants.STARTING_BACTERIA; i++) {
            bacteria.add(new Bacterium(PlagueConstants.STARTING_GENOTYPE,
                    rand.nextInt(PlagueConstants.X_RESOLUTION),
                    rand.nextInt(PlagueConstants.Y_RESOLUTION)));
        }
        this.funds = PlagueConstants.STARTING_FUNDS;
        thread = new Thread(() -> runGame());
        thread.start();
    }

    public static class RestartPlague extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            window.resetBacteriaPanel();
            instance.restart();
        }
    }
}
