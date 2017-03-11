package com.capitalistlepton.view;

import com.capitalistlepton.Plague;
import com.capitalistlepton.PlagueConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zanelittrell on 3/10/17.
 */
public class FundsPanel extends JPanel implements PlagueConstants{

    private Plague instance;

    public FundsPanel(Plague instance) {
        this.instance = instance;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(GAME_FONT);
        g.setColor(Color.WHITE);
        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.drawString("Available funds: $" + instance.funds(), 10, 25);
    }
}
