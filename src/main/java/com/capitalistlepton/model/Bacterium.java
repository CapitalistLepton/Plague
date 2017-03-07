package com.capitalistlepton.model;

import java.util.Random;

/**
 * Created by zanelittrell on 2/5/17.
 */
public class Bacterium {

    private static final Random rand = new Random();

    private int x;
    private int y;
    private String genotype;

    public Bacterium(String parentGenotype, int x, int y) {
        float mutation = rand.nextFloat();
        if (mutation > 0.99f) {
            genotype = "AABBCc";
        } else {
            genotype = parentGenotype;
        }
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getGenotype() { return genotype; }
}
