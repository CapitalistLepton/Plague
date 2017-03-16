package com.capitalistlepton;

import com.capitalistlepton.model.Antibiotic;
import com.capitalistlepton.model.BacteriaController;
import com.capitalistlepton.view.AntibioticCheckBox;
import com.capitalistlepton.view.MainWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class Plague implements ItemListener, PlagueConstants {

    private ArrayList<Antibiotic> activeAntibiotics;
    private int funds;
    private BacteriaController con;

    public static void main(String[] args) {
        BacteriaController con = new BacteriaController(STARTING_BACTERIA, STARTING_GENOTYPE);
        Plague p = new Plague(con, STARTING_FUNDS);
        MainWindow window = new MainWindow(con, p, false);
        try {
            while (p.turn()) {
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
    public static void reset(){
        Object[]  options = {"Yes, I must try again", "I quit!"};
        int choice =  JOptionPane.showOptionDialog(null, " Shall we play again?", "Plague",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1] );
        if (choice==0){
            newGame();
        }
        else{
            System.exit(0);
        }

    }
    public static void newGame(){
        
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
}
