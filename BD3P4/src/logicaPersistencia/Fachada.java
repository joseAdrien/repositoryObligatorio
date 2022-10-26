package logicaPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import logicaPersistencia.excepciones.*;
import logicaPersistencia.valueObjects.*;
import logicaPersistencia.accesoBD.AccesoBD;

public class Fachada extends UnicastRemoteObject implements IFachada{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url ;
	private String usuario ;
	private String password ;
	
	//Constructor
	public Fachada () throws RemoteException, PropertiesException{
		//Crear conexion a la base
		
		Properties p = new Properties();
		String nomArchi = "Config/config.properties";
		try {
			p.load (new FileInputStream (nomArchi));
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			usuario = p.getProperty("usuario");
			password = p.getProperty("password");
		
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			/* 2. una vez cargado el driver, me conecto con la base de datos */
		
		} catch (IOException e){
			throw new PropertiesException();
		}catch(ClassNotFoundException e) {
			throw new PropertiesException();
		}
		
	}
	
	//Requerimientos
	//Registrar un nuevo Dueño
	
	public void nuevoDuenio (VODuenio duenio) throws SQLException, nuevoDuenioException, RemoteException, ConectionException{
		
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
		
			int cedula = duenio.getCedula();
		
		
			if(!AccesoBD.existeDuenio(con, cedula)) {
				AccesoBD.crearDuenio(con, duenio);
			}else {
				throw new nuevoDuenioException();
			}
			con.close();
			
		}catch(SQLException e){
			
			throw new ConectionException();
		}
	}
	
	
	//Registrar una nueva mascota
	public void nuevaMascota(int cedula, VOMascota mascota) throws SQLException, ConectionException, noExisteDuenioException, RemoteException{
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, usuario, password);
			if(AccesoBD.existeDuenio(con, cedula)) {
				AccesoBD.crearMascota(con, cedula, mascota);
			}else {
				throw new noExisteDuenioException();
			}
		}catch(SQLException e){
			throw new ConectionException();
		}finally {
			System.out.println("cierro conexion");
			con.close();
		}
	}
	
	//Borrar dueño luego de borrar sus mascotas
	public void borrarDuenioMascotas(int cedula) throws SQLException, ConectionException, RemoteException, noExisteDuenioException{
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
		
			if(AccesoBD.existeDuenio(con, cedula)) {
				
				AccesoBD.borrarDuenioMascotas(con, cedula);
			}else {
				throw new noExisteDuenioException();
			}
			con.close();
				
		}catch(SQLException e){
			throw new ConectionException();
		}
	}	
	
	//Listar todos los duenios
	public List<VODuenio> listarDuenios() throws SQLException, ConectionException, RemoteException{
				
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			List<VODuenio> lista = AccesoBD.listarDuenios(con);
			
			con.close();
			return lista;
		}catch(SQLException e){
			throw new ConectionException();
		}
	}
	
	//Listar todas las mascotas de un duenio
	public List<VOMascotaList> listarMascotasDuenio(int cedula) throws noExisteDuenioException, SQLException, ConectionException, RemoteException{

		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			List<VOMascotaList> lista = null;
			
			if(AccesoBD.existeDuenio(con, cedula)) {
				lista = AccesoBD.listarMascotasDuenio(con, cedula);
			}else {
				throw new noExisteDuenioException();
			}
			con.close();
			return lista;
		}catch(SQLException e){
			throw new ConectionException();
		}
	}
	
	
	//Obtener una mascota
	public VOMascota obtenerMascota(int cedula, int numero) throws noExisteDuenioException, NoRelacionDueInsException, SQLException, ConectionException, RemoteException{
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			VOMascota mascota = null;
			if(AccesoBD.existeDuenio(con, cedula)) {
				if(AccesoBD.existeMascota(con, cedula, numero)) {
					mascota = AccesoBD.obtenerMascota(con, cedula, numero);
					
				}else {
					throw new NoRelacionDueInsException();
				}
			}else {
				throw new noExisteDuenioException();
			}
			con.close();
			return mascota;
		}catch(SQLException e){
			throw new ConectionException();
		}
	}	
	
	//Obtener la cantidad de mascotas de un dueño segun raza
	public int contarMascotas(int cedula, String raza) throws noExisteDuenioException, SQLException, ConectionException, RemoteException{
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			int cantidad = 0;
			
			if(AccesoBD.existeDuenio(con, cedula)) {
				cantidad = AccesoBD.contarMascotas(con, cedula, raza);
			}else {
				throw new noExisteDuenioException();
			}
			con.close();
			return cantidad;
		}catch(SQLException e){
			throw new ConectionException();
		}
	}	

}