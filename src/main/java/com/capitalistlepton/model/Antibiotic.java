package com.capitalistlepton.model;

import java.util.regex.Pattern;

/**
 * Created by zanelittrell on 3/6/17.
 */
public enum Antibiotic {

    PENECILIN("Pencilin", 8, 0.45f, Pattern.compile("^[a-zA-Z]{4}CC$"));

    private final String label;
    private final int cost;
    private final float effectiveness;
    private final Pattern targetGenotype;
    
    Antibiotic(String label, int cost, float effectiveness, Pattern targetGenotype) {
        this.label = label;
        this.cost = cost;
        this.effectiveness = effectiveness;
        this.targetGenotype = targetGenotype;
    }
    
    public String label() {
        return label;
    }

    public int cost() { return cost; }

    public float effectiveness() {
        return effectiveness;
    }

    public Pattern targetGenotype() {
        return targetGenotype;
    }
}
