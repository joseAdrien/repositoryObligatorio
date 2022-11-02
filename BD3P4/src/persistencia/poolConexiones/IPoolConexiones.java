package persistencia.poolConexiones;

import logica.excepciones.ConectionException;
import logica.excepciones.NotificadorPoolException;
import logica.excepciones.PersistenciaException;

public interface IPoolConexiones {
	
	public  IConexion obtenerConexion(boolean modifica) throws  PersistenciaException ;
	
	public void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException;
	

}
