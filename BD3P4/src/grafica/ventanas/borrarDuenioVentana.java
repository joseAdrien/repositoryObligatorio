package grafica.ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import javax.swing.JLabel;
import javax.swing.JTextField;
import grafica.controladores.borrarDuenioControlador;
import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.noExisteDuenioException;

public class borrarDuenioVentana {

	private JFrame frame1;
	private JTextField textFieldCedDue;
	
	private static borrarDuenioControlador cont;
	private static borrarDuenioVentana window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					borrarDuenioVentana window = new borrarDuenioVentana();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public borrarDuenioVentana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		
		frame1.setResizable(false);
		frame1.setSize(new Dimension(545, 571));
		frame1.setBounds(100, 100, 545, 571);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainAdmin();
				frame1.dispose();
			}
		});
		btnNewButton.setBounds(368, 489, 153, 30);
		frame1.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Cedula Dueño:");
		lblNewLabel.setBounds(10, 105, 205, 30);
		frame1.getContentPane().add(lblNewLabel);
		
		textFieldCedDue = new JTextField();
		textFieldCedDue.setBounds(192, 106, 280, 30);
		
		textFieldCedDue.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		      if(textFieldCedDue.getText().length()== 8)
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		
		frame1.getContentPane().add(textFieldCedDue);
		textFieldCedDue.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldCedDue.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "La cedula no puede estar vacia");
				}else {
					int cedula = Integer.parseInt(textFieldCedDue.getText());
					
					try {
						cont = new borrarDuenioControlador(window);
						cont.borrarDuenioCont(cedula);;
						JOptionPane.showMessageDialog(null, "Dueño eliminado correctamente");
						textFieldCedDue.setText(null);
					}catch (noExisteDuenioException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch(ConectionException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch(SQLException es){
						JOptionPane.showMessageDialog(null,"Error de conexión a la base de datos." + es.getMessage(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch (NotBoundException e1) {
						e1.printStackTrace();
					}catch (IOException e1) {
						e1.printStackTrace();
					} catch (PersistenciaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
		});
		btnNewButton_1.setBounds(368, 449, 153, 30);
		frame1.getContentPane().add(btnNewButton_1);
		frame1.setLocationRelativeTo(null);
		frame1.setTitle("Borrar Dueño");
		
		//despliego ventana
		frame1.setVisible(true);
	}
}