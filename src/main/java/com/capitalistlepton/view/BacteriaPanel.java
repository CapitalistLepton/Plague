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

        for (Bacterium b: bacteriaController.getBacteriaList()) {
            drawBacterium(g, bounds, b.getX(), b.getY());
        }
    }


    private void drawBacterium(Graphics g, int[] bounds, int x, int y) {
        Color temp = g.getColor();
        g.setColor(new Color(0xFF2662));
        if (x +  bounds[0] < bounds[1] && y + bounds[2] < bounds[3]) {
            // NOTE: The width and height parameters are currently set to match 640x480 resolution
            g.fillRect(x + bounds[0], y + bounds[2], 4, 4);
        }
        g.setColor(temp);
    }
}
