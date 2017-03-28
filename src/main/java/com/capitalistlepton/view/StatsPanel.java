package com.capitalistlepton.view;

import com.capitalistlepton.Plague;
import com.capitalistlepton.PlagueConstants;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by zanelittrell on 3/6/17.
 */
public class StatsPanel extends JPanel implements PlagueConstants {

    private Plague plague;
    private int lastCount;
    private DecimalFormat df;

    public StatsPanel(Plague plague) {
        this.plague = plague;
        lastCount = 1;
        df = new DecimalFormat("0.00000");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(GAME_FONT);
        g.setColor(Color.WHITE);
        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int count = plague.bacteriaCount();
        double lambda = ((double) count)/lastCount;
        lastCount = count;

        g.drawString("Number of bacteria: " + count, 10, 25);
        g.drawString("Lambda: " + df.format(lambda), 10, 45);

        g.drawString("Genotypes:", 10, 65);
        Map<String, Integer> genes = plague.getGenotypes();
        int y = 85;
        for (String gs: genes.keySet()) {
            g.drawString("[" + gs + ": " + genes.get(gs) + "]", 15, y);
            y += 20;
        }
    }

    public void reset(Plague plague) {
        this.plague = plague;
        repaint();
    }

}
