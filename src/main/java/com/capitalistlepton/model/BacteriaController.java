package com.capitalistlepton.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zanelittrell on 2/5/17.
 */
public class BacteriaController {

    private ArrayList<Bacterium> bacteria;
    private Random rand;

    public BacteriaController() {
        bacteria = new ArrayList<Bacterium>();
        rand = new Random();
        for (int i = 0; i < 50; i++) {
            // TODO add some kind of constant
            bacteria.add(new Bacterium("AABBCC", rand.nextInt(640), rand.nextInt(480)));
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
                Bacterium ba = new Bacterium(b.getGenotype(), rand.nextInt(640), rand.nextInt(480));
                bacteria.add(ba);
                i--;
            }
        }
    }

    public void useAntibiotic(Antibiotic a) {
        for (int i = 0; i < bacteria.size(); i++) {
            if (rand.nextFloat() < a.effectiveness() && bacteria.get(i).getGenotype().equals(a.targetGenotype())) {
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