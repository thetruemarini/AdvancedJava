package it.unibs.pajc;

import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

public class PnlMandelbrot extends JPanel implements
        MouseListener, MouseMotionListener {

    // private MandelbrotModel model = new MandelbrotModel();
    protected Rectangle2D.Double viewport = new Rectangle2D.Double(-2., -1., 3, 2);
    private Image mandelBrotImage = null;

    public void setData(double[][] data){

		mandelBrotImage = createImageFromData(data);
		repaint();

	}

    /* public void rebuildMandelBrot() {
        model.eval(
                new Complex(viewport.getMinX(), viewport.getMinY()),
                new Complex(viewport.getMaxX(), viewport.getMaxY()),
                250);
        // la res puo essere diminuita se la cpu non
        // regge

        mandelBrotImage = createImageFromData(model.getData());

        repaint();
    } */

    public Image createImageFromData(double[][] data){
        if (data == null)
            return null;
        int xSize = data.length;
        int ySize = data[0].length;

        Image img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) img.getGraphics();

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                double v = data[i][j];

                int c = (int) (v * 255);
                g2.setColor(new Color(c, c, c));

                g2.fillRect(j , i,  1, 1);
            }
        }

        return img;

    }

    private MandelbrotCntrl cntrl;
    public PnlMandelbrot(MandelbrotCntrl cntrl) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.cntrl = cntrl;
        this.cntrl.update(viewport, 1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        if(mandelBrotImage == null) return;

        g2.drawImage(mandelBrotImage, 0, 0, w, h, Color.white, null);


    }

    /* GESTIONE VIEWPORT */

    public void setViewport(Rectangle2D.Double newViewport) {
        this.viewport = newViewport;
        cntrl.update(newViewport, 1000);
       // repaint();
    }

    public void zoom(Point2D.Double p, double zoom){

        double finalWidth = viewport.getWidth() / zoom;
        double finalHeight = viewport.getHeight() / zoom;

        double rx = (p.x -viewport.getMinX()) / viewport.getWidth();
        double ry = (p.y -viewport.getMinY()) / viewport.getHeight();

        Rectangle2D.Double newViewport = new Rectangle2D.Double(
            p.x - finalWidth *rx,
            p.y - finalHeight * ry,
            finalWidth,
            finalHeight
        );

        setViewport(newViewport);

    }

    private Point panStartMousePosition = null;
    private Rectangle2D.Double panStartViewport = null;

    @Override
    public void mouseDragged(MouseEvent e) {
        double dx = (e.getX() - panStartMousePosition.x) 
        * panStartViewport.getWidth() / getWidth();
        double dy = (e.getY() - panStartMousePosition.y) 
        * panStartViewport.getHeight() / getHeight();

        Rectangle2D.Double newViewport = new Rectangle2D.Double(
                panStartViewport.x - dx,
                panStartViewport.y - dy,
                panStartViewport.width,
                panStartViewport.height);

        setViewport(newViewport);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D.Double pviewport = getViewportPoint(e.getPoint());
        if(e.isShiftDown())
        zoom(pviewport, 1/1.1);
        else zoom(pviewport, 1.1);
    }

    public Point2D.Double getViewportPoint (Point pixel){
        double x = viewport.getWidth() / getWidth() * pixel.x + viewport.getMinX();
        double y = viewport.getHeight() / getHeight() * pixel.y + viewport.getMinY();

        return new Point2D.Double(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        panStartMousePosition = null;
        panStartViewport = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (panStartMousePosition == null) {
            panStartMousePosition = e.getPoint();
            panStartViewport = viewport;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
