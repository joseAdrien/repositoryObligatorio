package grafica.ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import grafica.controladores.listarMascotasDuenioControlador;
import logica.excepciones.ConectionException;
import logica.excepciones.noExisteDuenioException;
import logica.valueObjects.VOMascotaList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JList;

public class listarMascotasDuenioVentana {

	private JFrame frame1;
	private JTextField textFieldCed;
	
	private static listarMascotasDuenioControlador cont;
	private static listarMascotasDuenioVentana window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listarMascotasDuenioVentana window = new listarMascotasDuenioVentana();
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
	public listarMascotasDuenioVentana() {
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
		
		JLabel lblApodoMascota = new JLabel("");
		lblApodoMascota.setBounds(231, 270, 45, 13);
		frame1.getContentPane().add(lblApodoMascota);
		
		JLabel lblRazaMascota = new JLabel("");
		lblRazaMascota.setBounds(231, 319, 45, 13);
		frame1.getContentPane().add(lblRazaMascota);
		frame1.setLocationRelativeTo(null);
		frame1.setTitle("Listar Mascotas de un dueño");
		
		JLabel lblNewLabel = new JLabel("Listado de Mascotas de un Dueño");
		lblNewLabel.setBounds(152, 57, 225, 13);
		frame1.getContentPane().add(lblNewLabel);
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		final JList<String> list = new JList<>(dlm);
		
		list.setBounds(23, 204, 482, 251);
		frame1.getContentPane().add(list);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainAdmin();
				frame1.dispose();
			}
		});
		btnNewButton.setBounds(402, 497, 85, 21);
		frame1.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Cedula del dueño: ");
		lblNewLabel_1.setBounds(23, 108, 116, 13);
		frame1.getContentPane().add(lblNewLabel_1);
		
		textFieldCed = new JTextField();
		textFieldCed.setBounds(192, 101, 285, 27);
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
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCed.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "La cedula no puede estar vacia");
				}else {
					int cedula = Integer.parseInt(textFieldCed.getText());
									
					//////////Voy a controlador
					///////////Cargo las lineas de las mascotas
					try {
						
						cont = new listarMascotasDuenioControlador(window);
						List<VOMascotaList> mascotas = cont.listarMascotasDuenioCont(cedula); //obtengo las mascotas del dueño y las almaceno en una lista
						if(mascotas.isEmpty()){
							String Linea = "El dueño no tiene mascotas ingresadas"; //Imprimo una linea con el mensaje
							dlm.addElement(Linea);
						}else {
							for (int i = 0; i<mascotas.size(); i++){ //Imprimo la lista
								VOMascotaList mascota = mascotas.get(i);
								String Linea = mascota.getNumInscripcion() + " - " + mascota.getApodo() + " - " + mascota.getRaza();
								dlm.addElement(Linea);
							}
						}
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
					}
				}
			}
		});
		btnConsultar.setBounds(363, 162, 124, 21);
		frame1.getContentPane().add(btnConsultar);
		
		
		//despliego ventana
		frame1.setVisible(true);
	}
}