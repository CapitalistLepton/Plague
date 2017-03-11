package com.capitalistlepton.view;

import com.capitalistlepton.Plague;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zanelittrell on 3/10/17.
 */
public class FundsPanel extends JPanel {

    private Plague instance;

    public FundsPanel(Plague instance) {
        this.instance = instance;
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

        g.drawString("Available funds: $" + instance.funds(), 10, 25);
    }
}
