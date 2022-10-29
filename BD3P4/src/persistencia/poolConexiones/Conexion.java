package persistencia.poolConexiones;

import java.sql.Connection;

public class Conexion implements IConexion {

	
	private Connection con;
	
	public Conexion (Connection connection) {
		this.con = connection;
	}
	
	public Connection getConexion() {
		return this.con;
	}
}
