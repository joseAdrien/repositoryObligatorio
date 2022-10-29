package persistencia.poolConexiones;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import logica.excepciones.ConectionException;
import logica.excepciones.PropertiesException;

public class PoolConexiones implements IPoolConexiones {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	private int nivelTransaccionalidad;
	private Conexion[] conexiones;//FEDE es de Conexiones o de Iconexiones
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
	public IConexion obtenerConexion(boolean modifica) throws ConectionException {
		if(hayConexiones()) {
			return getConexion();
		}else {
			return generarConexion();
		}
			
	}

	@Override
	public void liberarConexion(IConexion conexion , boolean ok) {
		if(ok) {
			//FEDE hacel roolback
		}
		
	}

	private boolean hayConexiones() {
		return creadas != 0;
	}
	
	private IConexion getConexion() {
		return conexiones[tope];
	}
	
	private IConexion generarConexion() throws ConectionException {
		try {//FEDE debemos preguntar si llegamos al tope?
			IConexion con =  (IConexion) DriverManager.getConnection(url, user, password);
			creadas = creadas ++;
			conexiones[tope]= (Conexion) con;
			tope = tope ++;
			return con;
		} catch (SQLException e) {
			throw new ConectionException();
		}
	}

}
