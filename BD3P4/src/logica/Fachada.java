package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logica.excepciones.PersistenciaException;
import logica.excepciones.noExisteDuenioException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;
import persistencia.abstracta.IFabricaAbstracta;
import persistencia.daos.DAODuenios;
import persistencia.daos.IDAODuenios;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada{//jose
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;


	
	private IDAODuenios miDaoDuenios;
	private IPoolConexiones miPool;
	
	private IConexion icon = null;
	
	
	//Constructor
	public Fachada () throws   RemoteException, PersistenciaException{

		 miDaoDuenios = new DAODuenios();
		 Properties p = new Properties();
		 	
			try {
			    String nomArchi = "Config/config.properties";
				String nombreFabrica = "";
				p.load (new FileInputStream (nomArchi));
				
				nombreFabrica= p.getProperty("fabrica");
				IFabricaAbstracta fabrica = (IFabricaAbstracta)Class.forName(nombreFabrica).newInstance();
				miDaoDuenios = fabrica.crearIDAODuenios();
			    miPool= fabrica.crearIPoolConexiones();
			   
			
			} catch (ClassNotFoundException e) {
				throw new PersistenciaException();
				
			} catch (FileNotFoundException e) {
				throw new PersistenciaException();
				
			} catch (IOException e) {
				throw new PersistenciaException();
				
			} catch (InstantiationException e) {
				throw new PersistenciaException();
				
			} catch (IllegalAccessException e) {
				throw new PersistenciaException();
			}
			
	}
	

		
	public void nuevoDuenio (VODuenio duenio) throws RemoteException, PersistenciaException,nuevoDuenioException {
			
		try {
				
			boolean existe = false;
			icon = miPool.obtenerConexion(true);
			existe = miDaoDuenios.member(icon, duenio.getCedula());
			if(!existe) {
				Duenio d = new Duenio(duenio.getCedula(),duenio.getNombre(),duenio.getApellido());
                miDaoDuenios.insert(icon, d);
			}//else{
			//	throw new nuevoDuenioException();
			//}
			
		
		} catch (noExisteDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw new PersistenciaException();
			
		} catch (nuevoDuenioException e) {
			miPool.liberarConexion(icon, false);
			throw e;
			
		} finally {
			miPool.liberarConexion(icon, true);
		}
		
	}
		

	public void nuevaMascota(int cedula, VOMascota mascota) throws RemoteException, PersistenciaException {
		
			
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
					masc = new Mascota(cantMAscotas + 1,mascota.getApodo(),mascota.getRaza());
					duenio.agregarMascota(icon, masc);
				}
			
			
			} catch (noExisteDuenioException e) {
				miPool.liberarConexion(icon, false);
				throw new PersistenciaException();

			
			} finally {
				miPool.liberarConexion(icon, true);
			}
			
			
	}
				

	public void borrarDuenioMascotas(int cedula) throws RemoteException, PersistenciaException  {
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


	public List<VODuenio> listarDuenios() throws RemoteException, PersistenciaException {
		List<VODuenio> duenios = new ArrayList<VODuenio>();
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
	

	public List<VOMascotaList> listarMascotasDuenio(int cedula) throws RemoteException,  PersistenciaException{

		List<VOMascotaList> mascotas = new ArrayList<VOMascotaList>();
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
	
	

	public VOMascota obtenerMascota(int cedula, int numero) throws RemoteException,  PersistenciaException{
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
	

	public int contarMascotas(int cedula, String raza) throws RemoteException,  PersistenciaException{
      
		int cantidadMascotas = 0;
		try {
			
			boolean existe = false;
			Duenio duenio = null;
			
			icon = miPool.obtenerConexion(false);
			existe = miDaoDuenios.member(icon, cedula);
			if(existe) {
				duenio = miDaoDuenios.find(icon, cedula);
				cantidadMascotas = duenio.contarMascotas(icon,raza);
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
