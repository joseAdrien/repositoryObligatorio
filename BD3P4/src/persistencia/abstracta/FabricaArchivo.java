package persistencia.abstracta;

import logica.excepciones.PersistenciaException;
import persistencia.daos.DAODuenioArchivo;
import persistencia.daos.DAOMAscotasArchivo;
import persistencia.daos.IDAODuenios;
import persistencia.daos.IDAOMascotas;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesArchivo;

public class FabricaArchivo implements IFabricaAbstracta {


	public IDAODuenios crearIDAODuenios() {
		return new DAODuenioArchivo();
	}

	@Override
	public IPoolConexiones crearIPoolConexiones() throws PersistenciaException {
		return new PoolConexionesArchivo();
	}

	@Override
	public IDAOMascotas crearIDAOMAscotas(int ced) {
		return new DAOMAscotasArchivo();
	}
}
