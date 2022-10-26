package logicaPersistencia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.excepciones.ConectionException;
import logicaPersistencia.excepciones.NoRelacionDueInsException;
import logicaPersistencia.excepciones.noExisteDuenioException;
import logicaPersistencia.excepciones.nuevoDuenioException;
import logicaPersistencia.valueObjects.*;
//import logicaPersistencia.valueObjects.VOMascotaList;

public interface IFachada extends Remote
{
	//Requerimientos
		//Registrar un nuevo Dueño
		public abstract void nuevoDuenio (VODuenio duenio) throws SQLException, nuevoDuenioException, RemoteException, ConectionException;
		
		//Registrar una nueva mascota
		public abstract void nuevaMascota(int cedula, VOMascota mascota) throws SQLException, ConectionException, noExisteDuenioException, RemoteException;
		
		//Borrar dueño luego de borrar sus mascotas
		public abstract void borrarDuenioMascotas(int cedula) throws SQLException, ConectionException, RemoteException, noExisteDuenioException;
		
		//Listar todos los duenios
		public abstract List<VODuenio> listarDuenios() throws SQLException, ConectionException, RemoteException;
		
		//Listar todas las mascotas de un duenio
		public abstract List<VOMascotaList> listarMascotasDuenio(int cedula) throws noExisteDuenioException, SQLException, ConectionException, RemoteException;
		
		//Obtener una mascota
		public abstract VOMascota obtenerMascota(int cedula, int numero) throws noExisteDuenioException, NoRelacionDueInsException, SQLException, ConectionException, RemoteException;
		
		//Obtener la cantidad de mascotas de un dueño segun raza
		public abstract int contarMascotas(int cedula, String raza) throws noExisteDuenioException, SQLException, ConectionException, RemoteException;
}
