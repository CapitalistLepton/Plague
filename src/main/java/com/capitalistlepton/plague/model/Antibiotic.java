package com.capitalistlepton.plague.model;

import java.util.regex.Pattern;

/**
 * Created by zanelittrell on 3/6/17.
 */
public enum Antibiotic {

    AMOXICILIN("Amoxicilin", 8, 0.45f, Pattern.compile("^[a-zA-Z]{4}CC$")),
    DOXYCYCLINE("Doxycycline", 40, 0.5f, Pattern.compile("^AA[a-zA-Z]{4}$")),
    TETRACYCLINE("Tetracycline", 60, 0.55f, Pattern.compile("^Aa[a-zA-Z]{4}$")),
    GENTAMICIN("Gentamicin", 100, 0.73f, Pattern.compile("^[a-zA-Z]{2}[a-z]{2}[a-zA-Z]{2}$"))
    ;

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
