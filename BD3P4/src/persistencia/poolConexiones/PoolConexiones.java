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
import logica.excepciones.PropertiesException;

public class PoolConexiones implements IPoolConexiones {//JOSE
	
	private String driver;
	private String url;
	private String user;
	private String password;
	private int nivelTransaccionalidad;
	private Conexion[] conexiones;
	private int tamanio;
	private int creadas;
	private int tope;


	public PoolConexiones() throws PropertiesException {
		Properties p = new Properties();
		
		try {
			String nomArchi = "Config/config.properties";
			
			p.load (new FileInputStream (nomArchi));
		    driver = p.getProperty("driver");
		    url = p.getProperty("url");
		    user = p.getProperty("usuario");
		    password = p.getProperty("password");
		    nivelTransaccionalidad= Integer.parseInt(p.getProperty("nivelTransaccionalidad"));
		    tamanio = Integer.parseInt(p.getProperty("tamanio"));
		    
			Class.forName(driver);
			conexiones = new Conexion[tamanio];
			tope = 0;
		
			
		
		} catch (ClassNotFoundException e) {
			throw new PropertiesException();
			
		} catch (FileNotFoundException e) {
			throw new PropertiesException();
			
		} catch (IOException e) {
			throw new PropertiesException();
		}
		
		
		
	}

	@Override
	public synchronized IConexion obtenerConexion(boolean modifica) throws ConectionException, NotificadorPoolException {
		if(hayConexionesDisponibles()) {
			return getConexion();
		}else if(sePuedenCrearConecciones()){
			return generarConexion();
		}else {
			try {
				this.wait();
			} catch (InterruptedException e) {
				throw new NotificadorPoolException();
			}
		}
		return null;
		
			
	}

	@Override
	public synchronized void liberarConexion(IConexion conexion, boolean ok) throws ConectionException {
		
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
				
				throw new ConectionException();
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
	
	private IConexion generarConexion() throws ConectionException {
		try {
			IConexion con =  (IConexion) DriverManager.getConnection(url, user, password);
			((Conexion) con).getConexion().setTransactionIsolation(nivelTransaccionalidad);
			creadas = creadas ++;
			tope = tope ++;
			return con;
		} catch (SQLException e) {
			throw new ConectionException();
		}
	}

}
