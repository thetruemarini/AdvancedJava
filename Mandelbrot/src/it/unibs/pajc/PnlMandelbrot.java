package it.unibs.pajc;

import java.awt.geom.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PnlMandelbrot extends JPanel {
    private MandelbrotModel model = new MandelbrotModel();
    protected Rectangle2D.Double viewport = new Rectangle2D.Double(-2., -1., 3, 2);

    public PnlMandelbrot() {
        model.eval(new Complex(viewport.getMinX(), viewport.getMinY()),
                new Complex(viewport.getMaxX(), viewport.getMaxY()), 250); // la res puo essere diminuita se la cpu non
                                                                           // regge
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        double[][] data = model.getData();

        int xSize = data.length;
        int ySize = data[0].length;

        int dx = w / xSize;
        int dy = h / ySize;

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                double v = data[i][j];

                int c = (int) (v * 255);
                g2.setColor(new Color(c, c, c));
                

                g2.fillRect(j * dx, i * dy, dx, dy);
            }
        }

    }
}
