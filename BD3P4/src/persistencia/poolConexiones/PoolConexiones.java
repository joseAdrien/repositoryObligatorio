package persistencia.poolConexiones;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import logica.excepciones.ConectionException;
import logica.excepciones.NotificadorPoolException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.PropertiesException;

public class PoolConexiones implements IPoolConexiones {//JOSE
	

	private String url;
	private String user;
	private String password;
	private int nivelTransaccionalidad;
	private Conexion[] conexiones;
	private int tamanio;
	private int creadas;
	private int tope;


	public PoolConexiones() throws PersistenciaException {
		Properties p = new Properties();
		
		try {
			String nomArchi = "Config/config.properties";
			
			p.load (new FileInputStream (nomArchi));
		    url = p.getProperty("url");
		    user = p.getProperty("usuario");
		    password = p.getProperty("password");
		    nivelTransaccionalidad= Integer.parseInt(p.getProperty("nivelTransaccionalidad"));
		    tamanio = Integer.parseInt(p.getProperty("tamanio"));
		    //TODO
			//Class.forName(driver);traer el driver sql del properties 
			conexiones = new Conexion[tamanio];
			tope = 0;
		
	
		} catch (FileNotFoundException e) {
			throw new PersistenciaException();
			
		} catch (IOException e) {
			throw new PersistenciaException();
		}
		
		
		
	}

	@Override
	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		IConexion con = null;
		
		while( con == null) {
			if(hayConexionesDisponibles()) {
				con = getConexion();
			}else if(sePuedenCrearConecciones()){
				con = generarConexion();
			}else {
				try {
					this.wait();
				} catch (InterruptedException e) {
					throw new PersistenciaException();
				}
			}
		}//end While
		return con;
			
			
	}

	@Override
	public synchronized void liberarConexion(IConexion conexion, boolean ok) throws PersistenciaException {
		
			try {
				if(ok) {
					((Conexion) conexion).getConexion().commit();
				}else {
					((Conexion) conexion).getConexion().rollback();
				}
				tope = tope ++;
				conexiones[tope]= (Conexion) conexion;
				this.notify();
				
			} catch (SQLException e) {
				
				throw new PersistenciaException();
			}
		
		 
		
	}
	
	private boolean sePuedenCrearConecciones() {
		return creadas < tamanio; 
	}

	private boolean hayConexionesDisponibles() {
		return tope != 0;
	}
	
	private IConexion getConexion() {
		Conexion con = conexiones[tope];
		tope = tope --;
		return con;
	}
	
	private IConexion generarConexion() throws PersistenciaException {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			IConexion c = new Conexion(conn);
			creadas = creadas ++;
			tope = tope ++;
			return c;
		} catch (SQLException e) {
			throw new PersistenciaException();
		}
	}

}
