package logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import logica.excepciones.PersistenciaException;
import logica.excepciones.PropertiesException;
import logica.excepciones.noExisteDuenioException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;
import persistencia.daos.DAODuenios;
import persistencia.daos.DAOMascotas;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada{//jose
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	
	private DAODuenios miDaoDuenios;
	private IPoolConexiones miPool;
	private IConexion icon = null;
	//Constructor
	public Fachada () throws PropertiesException , RemoteException{

		 miDaoDuenios = new DAODuenios();
		
	}
	
	//Requerimientos
		//Registrar un nuevo Dueño
		
	public void nuevoDuenio (VODuenio duenio) throws PersistenciaException {
			
		try {
				
			boolean existe = false;
			icon = miPool.obtenerConexion(true);
			existe = miDaoDuenios.member(icon, duenio.getCedula());
			if(!existe) {
				Duenio d = new Duenio(duenio.getCedula(),duenio.getNombre(),duenio.getApellido());
                miDaoDuenios.insert(icon, d);
			}
		
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} catch (nuevoDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} finally {
			miPool.liberarConexion(icon, true);
		}
		
	}
		
	
	public void nuevaMascota(int cedula, VOMascota mascota) throws PersistenciaException {
		
			
			try {
				
				boolean existe = false;
				Duenio duenio = null;
				Mascota masc = null;
				int cantMAscotas=0;
				icon = miPool.obtenerConexion(true);
				existe = miDaoDuenios.member(icon, cedula);
				if(existe) {
					duenio = miDaoDuenios.find(icon, cedula);
					cantMAscotas =  duenio.cantidadMascotas(icon);
					masc = new Mascota();
					masc.setApodo(mascota.getApodo());
					masc.setRaza(mascota.getRaza());
					masc.setNumlnsc( cantMAscotas + 1);
					duenio.agregarMascota(icon, masc);
				}
			
			
			} catch (noExisteDuenioException e) {
				miPool.liberarConexion(icon, false);
				throw new PersistenciaException();

			
			} finally {
				miPool.liberarConexion(icon, true);
			}
			
			
	}
				
	
	//Borrar dueño luego de borrar sus mascotas
	public void borrarDuenioMascotas(int cedula) throws PersistenciaException  {
		try {	
			Duenio duenio = null;
			icon = miPool.obtenerConexion(true);
			duenio = miDaoDuenios.find(icon, cedula);
			if(duenio != null) {
				duenio.borrarMascotas(icon);
				miDaoDuenios.delete(icon, cedula);
			}
		
		} catch (PersistenciaException e) {
			miPool.liberarConexion(icon, false);
			throw e;
			
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} finally {
			miPool.liberarConexion(icon, true);
		}
		
	}	
	
	//Listar todos los duenios
	public List<VODuenio> listarDuenios() throws PersistenciaException {
		List<VODuenio> duenios = null;
		try {	
			
			icon = miPool.obtenerConexion(false);
			duenios =miDaoDuenios.listarDuenios(icon);
			return duenios;
		
		} catch (PersistenciaException e) {
			miPool.liberarConexion(icon, false);
			throw e;
			
		
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} finally {
			miPool.liberarConexion(icon, true);
			
		}		
		
	}
	
	//Listar todas las mascotas de un duenio
	public List<VOMascotaList> listarMascotasDuenio(int cedula) throws  PersistenciaException{

		List<VOMascotaList> mascotas = null;
		try {
			
			boolean existe = false;
			Duenio duenio = null;
			
			icon = miPool.obtenerConexion(false);
			existe = miDaoDuenios.member(icon, cedula);
			if(existe) {
				duenio = miDaoDuenios.find(icon, cedula);
				mascotas =  duenio.listarMAscotas(icon);
			}
		
			return mascotas;
		
		
		} catch (PersistenciaException e) {
			miPool.liberarConexion(icon, false);
			throw e;
			
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} finally {
			miPool.liberarConexion(icon, true);
		}
	}
	
	
	//Obtener una mascota
	public VOMascota obtenerMascota(int cedula, int numero) throws  PersistenciaException{
		VOMascota voMascota = null;
		try {
			
			boolean existe = false;
			Duenio duenio = null;
			
			icon = miPool.obtenerConexion(false);
			existe = miDaoDuenios.member(icon, cedula);
			if(existe) {
				duenio = miDaoDuenios.find(icon, cedula);
				voMascota = duenio.obtenerMascota(icon,numero);
			}
		
			return voMascota;
		
		} catch (PersistenciaException e) {
			miPool.liberarConexion(icon, false);
			throw e;
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} finally {
			miPool.liberarConexion(icon, true);
		}
	}	
	
	//Obtener la cantidad de mascotas de un dueño segun raza
	public int contarMascotas(int cedula, String raza) throws  PersistenciaException{
      
		int cantidadMascotas = 0;
		try {
			
			boolean existe = false;
			Duenio duenio = null;
			
			icon = miPool.obtenerConexion(false);
			existe = miDaoDuenios.member(icon, cedula);
			if(existe) {
				duenio = miDaoDuenios.find(icon, cedula);
				cantidadMascotas = duenio.cantidadMascotas(icon);
			}
		
			return cantidadMascotas;
		
		} catch (PersistenciaException e) {
			miPool.liberarConexion(icon, false);
			throw e;
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} finally {
			miPool.liberarConexion(icon, true);
		}
	}	

}
