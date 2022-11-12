package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;

public interface IFachada extends Remote
{
	//Requerimientos
		//Registrar un nuevo Dueño
		public abstract void nuevoDuenio (VODuenio duenio) throws  PersistenciaException, ConectionException, RemoteException, nuevoDuenioException;
		
		//Registrar una nueva mascota
		public abstract void nuevaMascota(int cedula, VOMascota mascota) throws PersistenciaException, RemoteException;
		
		//Borrar dueño luego de borrar sus mascotas
		public abstract void borrarDuenioMascotas(int cedula) throws  PersistenciaException, RemoteException;
		
		//Listar todos los duenios
		public abstract List<VODuenio> listarDuenios() throws  PersistenciaException, RemoteException;
		
		//Listar todas las mascotas de un duenio
		public abstract List<VOMascotaList> listarMascotasDuenio(int cedula) throws  PersistenciaException, RemoteException;
		
		//Obtener una mascota
		public abstract VOMascota obtenerMascota(int cedula, int numero) throws  PersistenciaException, RemoteException;
		
		//Obtener la cantidad de mascotas de un dueño segun raza
		public abstract int contarMascotas(int cedula, String raza) throws  PersistenciaException, RemoteException;
}
