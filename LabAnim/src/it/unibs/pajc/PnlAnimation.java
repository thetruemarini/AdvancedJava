package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PnlAnimation extends JPanel {

	private int xpos;
	private Timer t;
	
	public PnlAnimation() {
		t = new Timer(10, e -> stepNext());
		t.start();
	}

	public Timer getTimer(){
		return this.t;
	}

	private int dx = 10;
	public void stepNext(){
		xpos += dx;

		if (xpos +100 > getWidth())
		dx =- 10;

		if (xpos<=0)
		dx = 10;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2.setColor(Color.RED);
		g2.fillRect(xpos, h/2, 100, 50);
	}

}
