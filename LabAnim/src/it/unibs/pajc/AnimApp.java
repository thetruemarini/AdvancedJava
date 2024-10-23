package it.unibs.pajc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimApp window = new AnimApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnimApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PnlAnimation pnlAnimation = new PnlAnimation();
		frame.getContentPane().add(pnlAnimation, BorderLayout.CENTER);

		JPanel south = new JPanel();
		frame.getContentPane().add(south, BorderLayout.SOUTH);

		JButton btnStep = new JButton("STEP");
		south.add(btnStep);
		btnStep.addActionListener(e -> pnlAnimation.stepNext());

		JButton btnStop = new JButton("STOP");
		south.add(btnStop);
		btnStop.addActionListener(e -> pnlAnimation.getTimer().stop());

		JButton btnStart = new JButton("START");
		south.add(btnStart);
		btnStart.addActionListener(e -> pnlAnimation.getTimer().restart());

	}



}
