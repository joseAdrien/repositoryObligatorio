package persistencia.poolConexiones;


public class ConexionArchivo implements IConexion {
	
	private String nomarchivo;
	
	public ConexionArchivo (String nomarchivo) {
		this.nomarchivo = nomarchivo;
	}
	
	public String getNomarchivo() {
		return this.nomarchivo;
	}

}
