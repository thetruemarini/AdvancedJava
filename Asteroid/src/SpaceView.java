import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceView extends JPanel implements KeyListener {

	BattleField universe = new BattleField();

	/**
	 * Create the panel.
	 */
	public SpaceView() {

		this.addKeyListener(this);

		Timer animator = new Timer(10, e -> {
			applyControls();
			universe.stepNext();
			repaint();
		}); //ogni 10 millisecondi voglio fare quell'azione
		animator.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		this.setFocusable(true);//pup ricevere gli eventi della tastiera
		this.requestFocusInWindow(); //forzo il fatto che lui è il target della tastiera
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//voglio far comparire la navicella al centro utilizzando le giuste trasformazioni affinche siano sulle misure di battlefield

		//voglio confinare il mondo in un mille per mille col fattore di scala, dim(viewport che voglio mappare)/coordinate mondo

		double s = Math.min(getWidth(), getHeight()/1000.);
		g2.scale(s, -s);
		g2.translate(500, -500);

		g2.setColor(Color.black);
		g2.fillRect(-500, -500, 1000, 1000);
		g2.setColor(Color.yellow);

		for(SpaceObject o: universe.listSpaceObjects()){ //per ogni oggetto dello spazio, disegnalo
			g2.draw(o.getShape());
		}
		



	}

	private ArrayList<Integer> currentActiveKeys = new ArrayList<>();

	private void applyControls(){
		SpaceShip s = universe.getSpaceShip();

		if(s== null)
		return;

		for(Integer keycode: currentActiveKeys){

			switch(keycode){
			case KeyEvent.VK_UP: //freccia su, l'astronave accellera
				s.accelerate(0.2f); //dipende anche lungo che direzione accellera, se è orientata
				break;
			case KeyEvent.VK_DOWN:
				s.accelerate(-0.2f);
				break;
			case KeyEvent.VK_RIGHT: s.turn(-0.1f); break;
			case KeyEvent.VK_LEFT: s.turn(0.1f); break;	
			case KeyEvent.VK_SPACE: s.fire(); break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { //premp e rilascio
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(!currentActiveKeys.contains(e.getKeyCode()))
			currentActiveKeys.add(e.getKeyCode());
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {//cosi quando la rilascio si toglie
		currentActiveKeys.remove((Integer)e.getKeyCode());
	}

}