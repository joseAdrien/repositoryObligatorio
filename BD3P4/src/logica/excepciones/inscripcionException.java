package logica.excepciones;

public class inscripcionException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public inscripcionException ()
	{
		this.mensaje = "No existe inscripción.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
