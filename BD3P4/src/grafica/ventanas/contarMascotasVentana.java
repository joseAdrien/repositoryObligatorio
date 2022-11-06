package grafica.ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import grafica.controladores.contarMascotasControlador;
import grafica.controladores.listarMascotasDuenioControlador;
import grafica.controladores.obtenerMascotaControlador;
import logica.excepciones.ConectionException;
import logica.excepciones.NoRelacionDueInsException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.noExisteDuenioException;
import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.List;

public class contarMascotasVentana {

	private JFrame frame1;
	private JTextField textFieldCed;
	private JTextField textFieldRaza;
	
	private static contarMascotasControlador cont;
	private static contarMascotasVentana window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					contarMascotasVentana window = new contarMascotasVentana();
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
	public contarMascotasVentana() {
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
		
		textFieldCed = new JTextField();
		textFieldCed.setBounds(162, 106, 310, 30);
		
		textFieldCed.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		      if(textFieldCed.getText().length()== 8)
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		
		frame1.getContentPane().add(textFieldCed);
		textFieldCed.setColumns(10);
		
		textFieldRaza = new JTextField();
		textFieldRaza.setColumns(10);
		textFieldRaza.setBounds(162, 168, 310, 30);	
		frame1.getContentPane().add(textFieldRaza);
		
		JLabel lblNInscripcin = new JLabel("Raza:");
		lblNInscripcin.setBounds(10, 167, 205, 30);
		frame1.getContentPane().add(lblNInscripcin);
		frame1.setLocationRelativeTo(null);
		frame1.setTitle("Obtener Mascota");
		
		JButton btnNewButton_1 = new JButton("Consultar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCed.getText().equals("") || textFieldRaza.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
				}else {
					int cedula = Integer.parseInt(textFieldCed.getText());
					String raza = textFieldRaza.getText();
					
					//////////Voy a controlador
					
					try {
						
						cont = new contarMascotasControlador(window);
						int mascotasCant = cont.contarMascotasCont(cedula, raza); //obtengo la mascotas especifica
						
						//imprimo la cantidad de mascotas
						JOptionPane.showMessageDialog(null, "La cantidad de mascotas es: " + Integer.toString(mascotasCant),"Resultado",JOptionPane.INFORMATION_MESSAGE);
					}catch(noExisteDuenioException e1) {
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
					
					textFieldCed.setText("");
					textFieldRaza.setText("");
				}
			}
		});
		btnNewButton_1.setBounds(368, 449, 153, 30);
		frame1.getContentPane().add(btnNewButton_1);
		
		//despliego ventana
		frame1.setVisible(true);
	}
}