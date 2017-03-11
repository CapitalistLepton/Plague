package com.capitalistlepton.model;

import com.capitalistlepton.PlagueConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zanelittrell on 2/5/17.
 */
public class BacteriaController implements PlagueConstants {

    private ArrayList<Bacterium> bacteria;
    private Random rand;

    public BacteriaController(int initialBacteria, String startingGenotype) {
        bacteria = new ArrayList<Bacterium>();
        rand = new Random();
        for (int i = 0; i < initialBacteria; i++) {
            bacteria.add(new Bacterium(startingGenotype, rand.nextInt(X_RESOLUTION), rand.nextInt(Y_RESOLUTION)));
        }
    }

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
                Bacterium ba = new Bacterium(b.getGenotype(), rand.nextInt(X_RESOLUTION), rand.nextInt(Y_RESOLUTION));
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
}