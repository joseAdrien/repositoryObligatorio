package grafica.ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainAdmin {

	private JFrame frame1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainAdmin window = new mainAdmin();
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
	public mainAdmin() {
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
		frame1.setTitle("Menú Principal - Administrador de Mascotas");
		///////////////////////////////////////////////////////////////
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 10, 521, 22);
		frame1.getContentPane().add(menuBar);
		
		JMenu mnNewMenuAcciones = new JMenu("Acciones");
		//mnNewMenuAcciones.setBounds(0, 10, 111, 24);
		menuBar.add(mnNewMenuAcciones);
		
		JMenu mnNewMenuReportes = new JMenu("Reportes");
		//mnNewMenuReportes.setBounds(0, 10, 111, 24);
		menuBar.add(mnNewMenuReportes);
		
		JMenu mnNewMenuSalir = new JMenu("Salir");
		mnNewMenuSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Salir();
			}
		});
		//mnNewMenuSalir.setBounds(0, 10, 111, 24);
		
		menuBar.add(mnNewMenuSalir);
		
		JMenuItem nuevoDuenioItem = new JMenuItem("Nuevo Dueño");
		nuevoDuenioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new nuevoDuenioVentana();
				frame1.dispose();
			}
		});
		mnNewMenuAcciones.add(nuevoDuenioItem);
		
		JMenuItem nuevaMascotaItem = new JMenuItem("Nueva Mascota");
		nuevaMascotaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new nuevaMascotaVentana();
				frame1.dispose();
			}
		});
		mnNewMenuAcciones.add(nuevaMascotaItem);
		
		JMenuItem eliminarDuenio = new JMenuItem("Quitar dueño de mascotas");
		eliminarDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new borrarDuenioVentana();
				frame1.dispose();
			}
		});
		mnNewMenuAcciones.add(eliminarDuenio);
		
		JMenuItem listarDuenio = new JMenuItem("Listar dueños");
		listarDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new listarDueniosVentana();
				frame1.dispose();
			}
		});
		mnNewMenuReportes.add(listarDuenio);
		
		JMenuItem listarMascotasDuenio = new JMenuItem("Listar mascotas de un dueño");
		listarMascotasDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new listarMascotasDuenioVentana();
				frame1.dispose();
			}
		});
		mnNewMenuReportes.add(listarMascotasDuenio);
		
		JMenuItem obtenerMascotaDuenio = new JMenuItem("Obtener mascota de un dueño");
		obtenerMascotaDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new obtenerMascotaVentana();
				frame1.dispose();
			}
		});
		mnNewMenuReportes.add(obtenerMascotaDuenio);
		
		JMenuItem contarMascotas = new JMenuItem("Contar mascotas segun dueño y raza");
		contarMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new contarMascotasVentana();
				frame1.dispose();
			}
		});
		mnNewMenuReportes.add(contarMascotas);
		
		//despliego ventana
		frame1.setVisible(true);
	}

//Evento Salir del sistema
	public static void Salir (){
		System.exit(0);
	}
}