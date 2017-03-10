package com.capitalistlepton;

import com.capitalistlepton.model.Antibiotic;
import com.capitalistlepton.model.BacteriaController;
import com.capitalistlepton.view.AntibioticCheckBox;
import com.capitalistlepton.view.MainWindow;

import javax.swing.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class Plague implements ItemListener {

    private ArrayList<Antibiotic> activeAntibiotics;
    private int funds;
    private BacteriaController con;

    public static void main(String[] args) {
        BacteriaController con = new BacteriaController();
        Plague p = new Plague(con, 5000);
        MainWindow window = new MainWindow(con, p, false);
        try {
            while (p.turn()) {
                window.repaint();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Plague(BacteriaController con, int funds) {
        this.activeAntibiotics = new ArrayList<Antibiotic>();
        this.funds = funds;
        this.con = con;
    }

    public boolean turn() {
        if (con.bacteriaCount() < 1300 && con.bacteriaCount() > 0) {
            con.replicate();
            for (Antibiotic activeAntibiotic : activeAntibiotics) {
                con.useAntibiotic(activeAntibiotic);
                // TODO: subtract funds for antibiotic
            }
        }
        return con.bacteriaCount() < 1300 && con.bacteriaCount() > 0;
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
}
