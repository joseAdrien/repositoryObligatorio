package persistencia.poolConexiones;

import logica.excepciones.ConectionException;

public interface IPoolConexiones {
	
	public IConexion obtenerConexion(boolean modifica) throws ConectionException ;
	
	public void liberarConexion(IConexion conexion, boolean ok);
	

}
