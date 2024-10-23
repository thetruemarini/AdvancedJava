package it.unibs.pajc;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class PnlDigit extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlDigit() {
		for(int i=9;i>=0;i--)
			addAction(""+ i);
	}

	public PnlDigit(List<String> labels){
		labels.forEach(this::addAction);
	}

	public void addAction(String action){
			JButton btn = new JButton(action);
			btn.setPreferredSize(new Dimension(45,45));
			this.add(btn);
			btn.addActionListener(this::fireActionPermed);
	}

	private ArrayList<ActionListener> listenerList = new ArrayList<>();
	
	public void addActionListener(ActionListener l){
		listenerList.add(l);
	}

	public void removeActionListener(ActionListener l){
		listenerList.remove(l);
	}

	public void fireActionPermed(ActionEvent e){
		e = new ActionEvent(this, e.getID(), e.getActionCommand());

		for(ActionListener l: listenerList){
			l.actionPerformed(e);
		}
	}

}
