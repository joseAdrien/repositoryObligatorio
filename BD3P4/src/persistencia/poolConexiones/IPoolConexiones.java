package persistencia.poolConexiones;

import logica.excepciones.ConectionException;
import logica.excepciones.NotificadorPoolException;

public interface IPoolConexiones {
	
	public  IConexion obtenerConexion(boolean modifica) throws ConectionException, NotificadorPoolException ;
	
	public void liberarConexion(IConexion conexion, boolean ok) throws ConectionException;
	

}
