package logica.excepciones;

public class PersistenciaException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public PersistenciaException ()
	{
		this.mensaje = "Ocurrió un error en la capa de acceso a datos.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
