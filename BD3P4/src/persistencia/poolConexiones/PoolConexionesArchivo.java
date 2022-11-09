package persistencia.poolConexiones;

import logica.excepciones.PersistenciaException;

public class PoolConexionesArchivo implements IPoolConexiones{

	@Override
	public IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		if(modifica) {
		//implementar escritor
		}else {
			//implementar lector
		}
		return null;
	}

	@Override
	public void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException {
		//ldespierto un escritor o despierto un lector segun la variable ok
		
	}

}
