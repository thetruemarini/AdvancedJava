package it.unibs.pajc;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.function.Function;

import javax.swing.JPanel;

public class PnlForceField extends JPanel implements MouseMotionListener {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public PnlForceField() {

        this.addMouseMotionListener(this);

    }

    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int w = getWidth();
		int h = getHeight();
		
        // paintForceField(g2, w, h);

        g2.translate(w/2, h/2);
        g2.scale(1,-1);
        
        g2.drawLine(0, h/2, 0, -h/2);
        g2.drawLine(-w/2, 0, w/2, 0);

        g2.scale(w/(Math.PI * 4), h/2.2);

        plotFunction(g2, -2* Math.PI, 2* Math.PI, 0.05, x -> (Math.sin(x)));

        g2.setColor(Color.red);

        plotFunction(g2, -2* Math.PI, 2* Math.PI, 0.05, x -> 0.5 * (Math.sin(x) + Math.cos(2*x)));
        
		
	}

    public void plotFunction(Graphics2D g2, double xmin, double xmax, double dx, Function<Double, Double> f ){
        for(double x = xmin; x<= xmax; x+=dx){
            double y = f.apply(x);
            circle(g2, x, y, 0.05f);
        }

    }

    private void circle(Graphics2D g2, double x, double y, double r){
        g2.fill(new Ellipse2D.Double(x-r,y-r,r*2,r*2));

    }

    /*
     * private void paintForceField(Graphics2D g2, int w, int h) {
     * for(int x = 25; x < w; x+= 25){
     * for(int y = 25; y< h; y+=25){
     * float angolo = (float) Math.atan2(mousePos.y- y, mousePos.x - x);
     * float dx = mousePos.x-x;
     * float dy = mousePos.x-y;
     * float tinta = (dx*dx + dy*dy) / (w*w+ h*h);
     * g2.setColor(Color.getHSBColor(tinta, 1f, 1f));
     * fillArrow(g2, x, y, 20, angolo);
     * 
     * }
     * }
     * //calcolo l'angolo di cui ci dobbiamo inclinare (tra la freccia e la
     * posizione del mouse
     * }
     */

    protected void fillArrow(Graphics2D g2, int x, int y, int size, float angolo) { // coordinate della freccia e size
        Path2D p = new Path2D.Float();

        p.moveTo(size / 2, 0); // spostati subito a dx
        p.lineTo(-size / 2, -size / 4); // traccia una linea a questo punto adesso
        p.lineTo(-size / 6, 0);
        p.lineTo(-size / 2, size / 4);
        p.closePath();

        // disegniamo la freccia in uno spazio assoluto nostro
        AffineTransform at = new AffineTransform();
        at.translate(x, y); // lo 0;0 viene proiettato a x y
        at.rotate(angolo);// lo ruoto dell'angolo tra la posizione della freccia e la posizione del mouse

        g2.fill(at.createTransformedShape(p)); // cosi disegno il mio path

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    private Point mousePos = new Point(0, 0); // non lo metto a null perche non ho voglia poi di fare i controlli nel
                                              // paint

    @Override
    public void mouseMoved(MouseEvent e) {

        mousePos = e.getPoint();
        repaint();
    }

}