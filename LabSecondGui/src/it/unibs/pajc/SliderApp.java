package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class SliderApp {

	private JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SliderApp window = new SliderApp();
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
	public SliderApp() {
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
		progressBar.setForeground(Color.MAGENTA);
		North.add(progressBar);
		progressBar.setValue(50);
		
		JPanel South = new JPanel();
		frame.getContentPane().add(South, BorderLayout.SOUTH);
		South.setLayout(new BorderLayout(0, 0));
		
		JSlider slider = new JSlider();
		South.add(slider);
		
		JPanel Center = new JPanel();
		frame.getContentPane().add(Center, BorderLayout.CENTER);
		Center.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 85, 150, 26);
		Center.add(passwordField);

		// lo slider non è un botton quindi cambia nel tempo e si tratta in modo
		// diverso

		slider.addChangeListener(e -> progressBar.setValue(slider.getValue()));
	
	}
}

