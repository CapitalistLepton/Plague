package com.capitalistlepton.view;

import com.capitalistlepton.model.BacteriaController;
import com.capitalistlepton.model.Bacterium;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zanelittrell on 2/3/17.
 */
public class BacteriaPanel extends JPanel {

    private static final int xRes = 640;
    private static final int yRes = 480;

    private BacteriaController bacteriaController;
    private String message;

    public BacteriaPanel(BacteriaController con) {
        bacteriaController = con;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xMargin = (getWidth() - xRes) / 2;
        int yMargin = (getHeight() - yRes) / 2;
//        g.setColor(new Color(0x00ff00));
//        g.fillRect(xMargin, yMargin, xRes, yRes);

        int minX = xMargin;
        int maxX = getWidth() - xMargin;
        int minY = yMargin;
        int maxY = getHeight() - yMargin;
        int[] bounds = {minX, maxX, minY, maxY};

        g.setColor(new Color(0xFF2662));
        for (Bacterium b: bacteriaController.getBacteriaList()) {
            drawBacterium(g, bounds, b.getX(), b.getY());
        }

        if (message != null) {
            g.setFont(new Font("Menlo", Font.PLAIN, 32));
            g.setColor(Color.WHITE);
            ((Graphics2D)g).setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawString(message, (minX+maxX)/2 - 40, (minY+maxY)/2);
        }
    }


    private void drawBacterium(Graphics g, int[] bounds, int x, int y) {
        if (x + bounds[0] < bounds[1] && y + bounds[2] < bounds[3]) {
            // NOTE: The width and height parameters are currently set to match 640x480 resolution
            g.fillRect(x + bounds[0], y + bounds[2], 4, 4);
        }
    }

    public void writeMessage(String message) {
        this.message = message;
        repaint();
    }
}
