package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import java.awt.Color;

public class GUIApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApp window = new GUIApp();
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
	public GUIApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel North = new JPanel();
		frame.getContentPane().add(North, BorderLayout.NORTH);
		North.setLayout(new BorderLayout(0, 0));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(51, 255, 51));
		North.add(progressBar);
		
		JPanel South = new JPanel();
		frame.getContentPane().add(South, BorderLayout.SOUTH);
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		South.add(slider);
		
		JPanel Center = new JPanel();
		frame.getContentPane().add(Center, BorderLayout.CENTER);
	}

}
