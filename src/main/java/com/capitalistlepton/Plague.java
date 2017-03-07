package com.capitalistlepton;

import com.capitalistlepton.model.Antibiotic;
import com.capitalistlepton.model.BacteriaController;
import com.capitalistlepton.view.MainWindow;

import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class Plague {

    public static void main(String[] args) {
        BacteriaController con = new BacteriaController();
        JFrame window = new MainWindow(con, false);
        boolean penicilinActive = false;
        try {
            while (con.bacteriaCount() < 13000) {
                con.replicate();
                if (con.bacteriaCount() > 500) {
                    penicilinActive = true;
                }
                if (penicilinActive) {
                    con.useAntibiotic(Antibiotic.PENECILIN);
                }
                window.repaint();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
