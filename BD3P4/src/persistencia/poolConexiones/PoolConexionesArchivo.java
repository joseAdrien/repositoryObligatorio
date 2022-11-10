package persistencia.poolConexiones;

import logica.excepciones.PersistenciaException;

public class PoolConexionesArchivo implements IPoolConexiones{
	

	private  int cantLectores;
	private  boolean escribiendo;
	
	public PoolConexionesArchivo() {
	cantLectores = 0;
	escribiendo = false;
	}


	@Override
	public IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		if(modifica) {
		//implementar escritor
			comienzoEscritura();
		}else {
			//implementar lector
			comienzoLectura();
		}
		return null;
	}

	@Override
	public void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException {
		//ldespierto un escritor o despierto un lector segun la variable ok
		
		if(escribiendo) {
			//despierto escritor
			terminoEscritura();
		} else {
			//despierto lector
			terminoLectura();
		}
		
	}




	
	private synchronized void comienzoLectura() {
		while (escribiendo) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		cantLectores ++;
	}
	

private synchronized void terminoLectura() {
		cantLectores --;
		if( cantLectores == 0 )
			notify();
	}
	

private synchronized void comienzoEscritura() {
		while (cantLectores != 0 || escribiendo) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		escribiendo = true;
	}
	

private synchronized void terminoEscritura() {
		escribiendo = false;
		notify();
	}
	
}
