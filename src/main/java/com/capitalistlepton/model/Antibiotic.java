package com.capitalistlepton.model;

/**
 * Created by zanelittrell on 3/6/17.
 */
public enum Antibiotic {

    PENECILIN("Pencilin", 0.45f, "AABBCC");

    private final String label;
    private final float effectiveness;
    private final String targetGenotype;
    
    Antibiotic(String label, float effectiveness, String targetGenotype) {
        this.label = label;
        this.effectiveness = effectiveness;
        this.targetGenotype = targetGenotype;
    }
    
    public String label() {
        return label;
    }

    public float effectiveness() {
        return effectiveness;
    }

    public String targetGenotype() {
        return targetGenotype;
    }
}
