package it.unibs.pajc;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;

public class PnlKeyPad extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlKeyPad() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		for(int i = 0; i<10; i++){
			JButton btn = new JButton(String.format("%d", i));
			this.add(btn);
			btn.addActionListener(e -> fireActionPermed(e));
		}


	}

	private ArrayList<ActionListener> listenerList = new ArrayList<>();

	public void addActionListener(ActionListener l){
		listenerList.add(l);
	}	

	public void fireActionPermed(ActionEvent e){
		System.out.println("PNLK" + e.getActionCommand());
		for(ActionListener l: listenerList){
			l.actionPerformed(e);
		}
	}
}
