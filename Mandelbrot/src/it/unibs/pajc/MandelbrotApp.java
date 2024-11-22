package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;

public class MandelbrotApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MandelbrotApp window = new MandelbrotApp();
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

	private MandelbrotModel model;
	private MandelbrotCntrl cntrl;
	PnlMandelbrot pnlMandelbrot;

	public MandelbrotApp() {
		model = new MandelbrotModel();
		model.addChangeListener(this::updateModel);
		cntrl = new MandelbrotCntrl(model);

		initialize();
	}

	private void updateModel(ChangeEvent e) {
		if (pnlMandelbrot == null)
			return;

		double data[][] = model.getData();

		Runnable task = () -> pnlMandelbrot.setData(data);

		if (EventQueue.isDispatchThread()) {
			task.run();
		} else {
			SwingUtilities.invokeLater(task); //prende il task e lo mette nella coda di eventi 
			// awt che lo sincronizza automaticamente 
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlMandelbrot = new PnlMandelbrot(cntrl);
		frame.getContentPane().add(pnlMandelbrot, BorderLayout.CENTER);
	}

}
