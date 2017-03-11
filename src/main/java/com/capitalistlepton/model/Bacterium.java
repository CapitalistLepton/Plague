package com.capitalistlepton.model;

import java.util.Random;

/**
 * Created by zanelittrell on 2/5/17.
 */
public class Bacterium {

    private static final Random rand = new Random();
    /** The inverse of the rate of mutation (1 - rate of mutation). */
    private static final float RATE_OF_MUTATION = 0.99f;

    private int x;
    private int y;
    private String genotype;

    public Bacterium(String parentGenotype, int x, int y) {
        this.genotype = generateGenotype(parentGenotype);
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getGenotype() { return genotype; }

    private static String generateGenotype(String parentGenotype) {
        StringBuilder gen = new StringBuilder(parentGenotype);
        for (int i = 0; i < parentGenotype.length(); i += 2) {
            if (rand.nextFloat() > RATE_OF_MUTATION) {
                if (rand.nextFloat() > 0.75f) {
                    gen.setCharAt(i, Character.toLowerCase(gen.charAt(i)));
                    gen.setCharAt(i+1, Character.toLowerCase(gen.charAt(i+1)));
                } else if(rand.nextFloat() > 0.5f) {
                    gen.setCharAt(i, Character.toTitleCase(gen.charAt(i)));
                    gen.setCharAt(i+1, Character.toLowerCase(gen.charAt(i+1)));
                } else {
                    gen.setCharAt(i, Character.toUpperCase(gen.charAt(i)));
                    gen.setCharAt(i+1, Character.toUpperCase(gen.charAt(i+1)));
                }
            }
        }
        return gen.toString();
    }
}
