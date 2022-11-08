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

public class obtenerMascotaVentana {

	private JFrame frame1;
	private JTextField textFieldCed;
	private JTextField textFieldInscrip;
	
	private static obtenerMascotaControlador cont;
	private static obtenerMascotaVentana window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					obtenerMascotaVentana window = new obtenerMascotaVentana();
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
	public obtenerMascotaVentana() {
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
		textFieldCed.setBounds(94, 106, 378, 30);
		
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
		
		textFieldInscrip = new JTextField();
		textFieldInscrip.setColumns(10);
		textFieldInscrip.setBounds(94, 168, 378, 30);
		
		textFieldInscrip.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		      if(textFieldInscrip.getText().length()== 4)
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		
		frame1.getContentPane().add(textFieldInscrip);
		
		JLabel lblNInscripcin = new JLabel("N° Inscripción:");
		lblNInscripcin.setBounds(10, 167, 205, 30);
		frame1.getContentPane().add(lblNInscripcin);
		
		JLabel lblNewLabel_1 = new JLabel("Apodo: ");
		lblNewLabel_1.setBounds(94, 270, 45, 13);
		frame1.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Raza: ");
		lblNewLabel_1_1.setBounds(94, 319, 45, 13);
		frame1.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblApodoMascota = new JLabel("");
		lblApodoMascota.setBounds(231, 270, 45, 13);
		frame1.getContentPane().add(lblApodoMascota);
		
		JLabel lblRazaMascota = new JLabel("");
		lblRazaMascota.setBounds(231, 319, 45, 13);
		frame1.getContentPane().add(lblRazaMascota);
		frame1.setLocationRelativeTo(null);
		frame1.setTitle("Obtener Mascota  Grupo Sala 5");
		
		JButton btnNewButton_1 = new JButton("Consultar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldCed.getText().equals("") || textFieldInscrip.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
				}else {
					int cedula = Integer.parseInt(textFieldCed.getText());
					int inscripcion = Integer.parseInt(textFieldInscrip.getText());
					
					//////////Voy a controlador
					
					try {
						
						cont = new obtenerMascotaControlador(window);
						VOMascota mascota = cont.obtenerMascotaCont(cedula, inscripcion); //obtengo la mascotas especifica
						if(mascota == null) {
							JOptionPane.showMessageDialog(null, "No hay datos para esa cedula- inscripcion ","Advertencia",JOptionPane.WARNING_MESSAGE);
						}else {
							//imprimo el nombre de la mascota
							lblApodoMascota.setText(mascota.getApodo());
							lblRazaMascota.setText(mascota.getRaza());
						}
					}catch(noExisteDuenioException e1) {
						JOptionPane.showMessageDialog(null, e1.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
					}catch (NoRelacionDueInsException e1) {
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
		
		//despliego ventana
		frame1.setVisible(true);
	}
}