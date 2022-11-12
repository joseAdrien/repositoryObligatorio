package persistencia.abstracta;

import logica.excepciones.PersistenciaException;
import persistencia.daos.IDAODuenios;
import persistencia.daos.IDAOMascotas;
import persistencia.poolConexiones.IPoolConexiones;

public interface IFabricaAbstracta {

	public IDAODuenios crearIDAODuenios ();
	
	public IPoolConexiones crearIPoolConexiones() throws PersistenciaException;
	public IDAOMascotas crearIDAOMAscotas(int ced);

}
