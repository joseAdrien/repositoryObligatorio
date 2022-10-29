package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import logica.excepciones.*;
import logica.valueObjects.*;
import persistencia.accesoBD.AccesoBD;
import persistencia.daos.DAOMascotas;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.PoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada{//jose
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	
	private PoolConexiones miPool;
	private DAOMascotas miDaoMascotas;
	
	//Constructor
	public Fachada () throws RemoteException, PropertiesException{
		 miPool = new PoolConexiones();
		 miDaoMascotas = new DAOMascotas();
	}
	
	
	//Registrar una nueva mascota
		public void nuevaMascota(int cedula, VOMascota mascota) throws SQLException, ConectionException, noExisteDuenioException, RemoteException{
			
				IConexion icon = null;
				
				icon = miPool.obtenerConexion(true);
				try {
				//verificar si existe el duenio (Duenio.isMember)
				//obtener el duenio (duenio.find)
				//pedir cantidad de mascotas del duenio, agregarle 1 y setear ese valor a mascota.numero inscripcion
				//generar una instancia de mascoota
				//llamara a duenio.guardarmascota ( mascota , conexion)
				
				}catch () {
				  miPool.liberarConexion(icon, false);
				}finally (){
					miPool.liberarConexion(icon, true);
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