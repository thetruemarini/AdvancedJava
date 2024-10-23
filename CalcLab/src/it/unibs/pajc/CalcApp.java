package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class CalcApp {

	private JFrame frame;
	private JTextField txtResult;
	private CalcModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalcApp window = new CalcApp();
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
	public CalcApp() {
		model = new CalcModel();
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
		
		JPanel pnlNorth = new JPanel();
		frame.getContentPane().add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new BorderLayout(0, 0));
		
		txtResult = new JTextField();
		txtResult.setHorizontalAlignment(SwingConstants.LEFT);
		pnlNorth.add(txtResult);
		txtResult.setColumns(10);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(51, 153, 255));
		pnlSouth.setForeground(new Color(51, 153, 255));
		frame.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		
		JLabel lblInfo = new JLabel("CALCOLAMI LA CIOLA");
		pnlSouth.add(lblInfo);
		
		JPanel pnlCenter = new JPanel();
		frame.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(null);
		
		PnlDigit pnlDigit = new PnlDigit();
		pnlDigit.setBounds(273, 6, 171, 208);
		pnlCenter.add(pnlDigit);
		
		PnlDigit pnlOperator = new PnlDigit(model.listOperators());
		pnlOperator.setBounds(40, 26, 152, 165);
		pnlCenter.add(pnlOperator);

		pnlDigit.addActionListener(e -> {
			 txtResult.setText(txtResult.getText() + e.getActionCommand());
			 lblInfo.setText("Tasto premuto: " + e.getActionCommand());
			 System.out.println(e.getSource());
			}
		);
	}

}
