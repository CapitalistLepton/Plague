package com.capitalistlepton.view;

import com.capitalistlepton.model.BacteriaController;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by zanelittrell on 3/6/17.
 */
public class StatsPanel extends JPanel{

    private BacteriaController bacteriaController;
    private int lastCount;
    private DecimalFormat df;

    public StatsPanel(BacteriaController con) {
        bacteriaController = con;
        lastCount = 1;
        df = new DecimalFormat("0.00000");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g.setFont(new Font("Menlo", Font.PLAIN, 12));
        g.setColor(Color.WHITE);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int count = bacteriaController.bacteriaCount();
        double lambda = ((double) count)/lastCount;
        lastCount = count;

        g.drawString("Number of bacteria: " + count, 10, 25);
        g.drawString("Lambda: " + df.format(lambda), 10, 45);

        g.drawString("Genotypes:", 10, 65);
        Map<String, Integer> genes = bacteriaController.getGenotypes();
        int y = 85;
        for (String gs: genes.keySet()) {
            g.drawString("[" + gs + ": " + genes.get(gs) + "]", 15, y);
            y += 20;
        }
    }

}
