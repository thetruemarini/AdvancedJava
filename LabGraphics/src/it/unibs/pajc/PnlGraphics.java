package it.unibs.pajc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PnlGraphics extends JPanel
	implements MouseMotionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlGraphics() {
		this.addMouseMotionListener(this);
	}

	private int c = 0;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int w = getWidth();
		int h = getHeight();

		int dx = w / 8;
		int dy = h / 8;
		
		dx = Math.min(dx, dy);
		dy = dx;
		
		/* for(int x=0; x<w; x+=dx)
			g.drawLine(x, 0, x, h);

		for(int y=0; y<h; y+=dy)
			g.drawLine(0, y, w, y);
 */
		g.setColor(Color.ORANGE);
		
		/* for(int x=0; x<=w; x+=2*dx){
			for(int y=0; y<=h; y+=2*dy){
				g.fillRect(x, y, dx, dy);
			}
		} */

		for(int i=0; i<8; i++){
			for(int j=0;j<8;j++){
				Color c = (i+j) % 2 == 0 ? Color.ORANGE : Color.WHITE;
				g.setColor(c);
				g.fillRect(j*dx, i*dy, dx, dy);
			}
		}

		if(mousePosition != null){
			g.setColor(Color.DARK_GRAY);
			g.fillOval(mousePosition.x-(25/2), mousePosition.y-(25/2), 25, 25);
			// g.fillRect(mousePosition.x/dx * dx, mousePosition.y/dy * dy, dx, dy);

			g2.setStroke(new BasicStroke(5));

			g.drawRect(mousePosition.x/dx * dx, mousePosition.y/dy * dy, dx, dy);
			g.setColor(Color.MAGENTA);
			g.drawString(String.format("repaint: %d", c), 5, h-5);
			c++;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
	}

	private Point mousePosition = null;

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
		repaint();
		System.out.printf("mouse : %d, %d\n", mousePosition.x, mousePosition.y);
	}

}
