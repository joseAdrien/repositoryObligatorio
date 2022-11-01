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
import persistencia.daos.DAODuenios;
import persistencia.daos.DAOMascotas;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada{//jose
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	
	private DAOMascotas miDaoMascotas;
	private DAODuenios miDaoDuenios;
	private IPoolConexiones miPool;
	
	//Constructor
	public Fachada () throws PropertiesException , RemoteException{

		 miDaoMascotas = new DAOMascotas();
		 miDaoDuenios = new DAODuenios();
	}
	
	
		public void nuevaMascota(int cedula, VOMascota mascota) throws ConectionException, NotificadorPoolException, noExisteDuenioException {
			
				IConexion icon = null;
				try {
					
					boolean existe = false;
					Duenio duenio = null;
					Mascota masc = null;
					icon = miPool.obtenerConexion(true);
					existe = miDaoDuenios.member(icon, cedula);
					if(existe) {
						duenio = miDaoDuenios.find(icon, cedula);
						//pedir cantidad de mascotas del duenio, agregarle 1 y setear ese valor a mascota.numero inscripcion
						//generar una instancia de mascoota
						masc = new Mascota();
						masc.setApodo(mascota.getApodo());
						masc.setRaza(mascota.getRaza());
						//llamara a duenio.guardarmascota ( mascota , conexion)
					}
				} catch (ConectionException e) {
					miPool.liberarConexion(icon, false);
				} catch (NotificadorPoolException e) {
					miPool.liberarConexion(icon, false);
				} catch (noExisteDuenioException e) {
					miPool.liberarConexion(icon, false);
				} finally {
					miPool.liberarConexion(icon, true);
				}
				
				
		}
				
				
				

		
	//Requerimientos
	//Registrar un nuevo Dueño
	
	public void nuevoDuenio (VODuenio duenio) throws ConectionException, NotificadorPoolException, noExisteDuenioException, nuevoDuenioException {
		
		IConexion icon = null;
		try {
			
			boolean existe = false;
			icon = miPool.obtenerConexion(true);
			existe = miDaoDuenios.member(icon, duenio.getCedula());
			if(!existe) {
				Duenio d = new Duenio(duenio.getCedula(),duenio.getNombre(),duenio.getApellido());
                miDaoDuenios.insert(icon, d);
			}
		} catch (ConectionException e) {
			miPool.liberarConexion(icon, false);
			throw e;
		} catch (NotificadorPoolException e) {
			miPool.liberarConexion(icon, false);
			throw e;
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw e;
		} catch (nuevoDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw e;
		} finally {
			miPool.liberarConexion(icon, true);
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
