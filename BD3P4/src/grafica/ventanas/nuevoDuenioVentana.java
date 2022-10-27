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

import grafica.controladores.NuevoDuenioControlador;
import logica.excepciones.ConectionException;
import logica.excepciones.nuevoDuenioException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

public class nuevoDuenioVentana {

	private JFrame frame1;
	private JTextField textFieldCed;
	private JTextField textFieldNom;
	private JTextField textFieldApe;
	
	private static NuevoDuenioControlador cont;
	private static nuevoDuenioVentana window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nuevoDuenioVentana window = new nuevoDuenioVentana();
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
	public nuevoDuenioVentana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		
		
		/////////////////////////////////////////////////////////////////
		
		frame1.setResizable(false);
		frame1.setSize(new Dimension(545, 571));
		frame1.setBounds(100, 100, 545, 571);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainAdmin();
				frame1.dispose();
			}
		});
		btnNewButton.setBounds(368, 489, 153, 30);
		frame1.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Cedula:");
		lblNewLabel.setBounds(10, 105, 205, 30);
		frame1.getContentPane().add(lblNewLabel);
		
		textFieldCed = new JTextField();
		textFieldCed.setBounds(178, 106, 294, 30);
		
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
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(178, 164, 294, 30);
		frame1.getContentPane().add(textFieldNom);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 163, 205, 30);
		frame1.getContentPane().add(lblNombre);
		
		textFieldApe = new JTextField();
		textFieldApe.setColumns(10);
		textFieldApe.setBounds(178, 231, 294, 30);
		frame1.getContentPane().add(textFieldApe);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1.setBounds(10, 230, 205, 30);
		frame1.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCed.getText().equals("") || textFieldNom.getText().equals("") || textFieldApe.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio");
				}else {
					int cedula = Integer.parseInt(textFieldCed.getText());
					String apellido = textFieldApe.getText();
					String nombre = textFieldNom.getText();
					
					//////////Voy a controlador
					
					try {
						cont = new NuevoDuenioControlador(window);
						cont.guardarNuevoDuenioCont(cedula, nombre, apellido);
						JOptionPane.showMessageDialog(null, "Dueño ingresado correctamente");
						textFieldCed.setText(null);
						textFieldApe.setText(null);
						textFieldNom.setText(null);
					}catch (nuevoDuenioException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch(ConectionException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch(SQLException es){
						JOptionPane.showMessageDialog(null,"Error de conexión a la base de datos." + es.getMessage(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch (NotBoundException e1) {
						e1.printStackTrace();
					}catch (IOException e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		btnNewButton_1.setBounds(368, 449, 153, 30);
		frame1.getContentPane().add(btnNewButton_1);
		
		frame1.setLocationRelativeTo(null);
		frame1.setTitle("Nuevo Dueño");
		
		//despliego ventana
		frame1.setVisible(true);
	}
}