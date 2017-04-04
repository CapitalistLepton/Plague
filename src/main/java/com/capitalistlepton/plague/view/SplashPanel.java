package com.capitalistlepton.plague.view;

import com.capitalistlepton.plague.PlagueConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zanelittrell on 4/3/17.
 */
public class SplashPanel extends JPanel {

    public void logoState() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0x999999));
        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setFont(PlagueConstants.LOGO_FONT);
        g.drawString("Plague", 150, 200);

        g.setFont(PlagueConstants.BIG_GAME_FONT);
        g.drawString("v0.0.1", 190, 250);
    }
}
