package it.unibs.pajc;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AppGui {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGui window = new AppGui();
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
	public AppGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		PnlKeyPad pnlKeyPad = new PnlKeyPad();
		pnlKeyPad.setBounds(112, 77, 223, 531);
		frame.getContentPane().add(pnlKeyPad);
		
		textField = new JTextField();
		textField.setBounds(112, 6, 223, 65);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		pnlKeyPad.addActionListener(
			e -> System.out.println(e.getActionCommand())
		);

		pnlKeyPad.addActionListener(
			e -> textField.setText(e.getActionCommand())
		);
	}
}
