package it.unibs.pajc;

	import java.awt.Color;
	import java.awt.EventQueue;
	import java.awt.Font;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JButton;
	import javax.swing.JTextField;
	import javax.swing.JComboBox;
	import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class FirstGUIApp {

		private JFrame frame;
		private JTextField textField;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FirstGUIApp window = new FirstGUIApp();
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
		public FirstGUIApp() {
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
			
			JLabel lbInfo = new JLabel("Nessuna informazione");
			lbInfo.setFont(new Font("Algerian", Font.PLAIN, 16));
			lbInfo.setForeground(new Color(255, 150, 70));
			frame.getContentPane().add(lbInfo, BorderLayout.NORTH);
			
			JButton btnUno = new JButton("Uno");
			frame.getContentPane().add(btnUno);
			
			lbInfo.setText("Premi un bottone per vedere un cambiamento"); //metodo che permette di specificare il testo dell'etichetta
			
			JButton btnDue = new JButton("Due");
			frame.getContentPane().add(btnDue);
			
			JButton btnTre = new JButton("PROVA");
			frame.getContentPane().add(btnTre);

			textField = new JTextField();
			frame.getContentPane().add(textField);
			textField.setColumns(10);

			JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.SOUTH);
			
			JButton btnSend = new JButton("send\n");
			panel.add(btnSend);
			
			
			JComboBox cbColor = new JComboBox();
			cbColor.setModel(new DefaultComboBoxModel(ColoriStandard.values()));
			frame.getContentPane().add(cbColor, BorderLayout.WEST);
			
			ActionListener listener = e -> {
				
				if(e.getSource() instanceof JButton) {
					JButton source = (JButton) e.getSource();
					lbInfo.setText(String.format("Bottone %s premuto", source.getText()));
				}
			}; 
			
			//l'evento viene scatenato da qualcosa, il source ce lo dice. è utile perche se uso due bottoni cosi so
			//qual è il bottone!!
			
			btnUno.addActionListener(listener);
			
			//pero le closure catturano le effective final. 
			//se modifico lbinfo non va bene dopo. per ora non viene mai riassegnata quindi la reference non viene 
			//cambiata quindi la variabile non è alterata, al massimo coi set cambio le proprietà interne


			
			btnDue.addActionListener(listener);
			btnTre.addActionListener(listener);
			btnTre.addActionListener(e -> lbInfo.setForeground(Color.BLUE));
			btnSend.addActionListener(e -> lbInfo.setText(textField.getText()));
			cbColor.addActionListener(e -> {
				ColoriStandard c = (ColoriStandard) cbColor.getSelectedItem();
				// lbInfo.setText(""+c);
				lbInfo.setForeground(c.getColor());
			});
		}

}

