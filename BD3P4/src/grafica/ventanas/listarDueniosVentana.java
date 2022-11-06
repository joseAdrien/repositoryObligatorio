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

import grafica.controladores.borrarDuenioControlador;
import grafica.controladores.listarDueniosControlador;
import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.noExisteDuenioException;
import logica.valueObjects.VODuenio;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JList;

public class listarDueniosVentana {

	private JFrame frame1;
	
	private static listarDueniosControlador cont;
	private static listarDueniosVentana window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listarDueniosVentana window = new listarDueniosVentana();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws PersistenciaException 
	 */
	public listarDueniosVentana() throws PersistenciaException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws PersistenciaException 
	 */
	private void initialize() throws PersistenciaException {
		frame1 = new JFrame();
		
		
		frame1.setResizable(false);
		frame1.setSize(new Dimension(545, 571));
		frame1.setBounds(100, 100, 545, 571);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setLocationRelativeTo(null);
		frame1.setTitle("Listar Dueños");
		
		JLabel lblNewLabel = new JLabel("Listado de Dueños");
		lblNewLabel.setBounds(152, 57, 124, 13);
		frame1.getContentPane().add(lblNewLabel);
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		final JList<String> list = new JList<>(dlm);
		
		
		///////////Cargo las lineas de los dueños
		try {
			
			cont = new listarDueniosControlador(window);
			List<VODuenio> duenios = cont.listarDueniosCont();;
			if(duenios.isEmpty()){
				String Linea = "No hay dueños ingresados";
				dlm.addElement(Linea);
			}else {
				for (int i = 0; i<duenios.size(); i++){
					VODuenio duenio = duenios.get(i);
					String Linea = duenio.getCedula() + " - " + duenio.getNombre() + " - " +  duenio.getApellido();
					dlm.addElement(Linea);
				}
			}
		}catch(ConectionException e1) {
			JOptionPane.showMessageDialog(null, e1.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
		}catch(SQLException es){
			JOptionPane.showMessageDialog(null,"Error de conexión a la base de datos." + es.getMessage(),"Advertencia",JOptionPane.WARNING_MESSAGE);
		}catch (NotBoundException e1) {
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		list.setBounds(22, 105, 482, 382);
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
		
		//despliego ventana
		frame1.setVisible(true);
	}
}