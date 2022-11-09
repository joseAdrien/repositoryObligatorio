package persistencia.abstracta;

import logica.excepciones.PersistenciaException;
import persistencia.daos.DAODuenios;
import persistencia.daos.DAOMascotas;
import persistencia.daos.IDAODuenios;
import persistencia.daos.IDAOMascotas;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class FabricaMYSQL implements IFabricaAbstracta {

	
	public IDAODuenios crearIDAODuenios() {
		return new DAODuenios();
	}

	@Override
	public IDAOMascotas crearIDAOMAscotas(int ced) {
		return new DAOMascotas(ced);
	}

	@Override
	public IPoolConexiones crearIPoolConexiones()  throws PersistenciaException{
		
			return new PoolConexiones();
		
	}
}
