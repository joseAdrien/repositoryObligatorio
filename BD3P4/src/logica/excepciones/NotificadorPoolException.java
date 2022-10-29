package logica.excepciones;

public class NotificadorPoolException extends Exception{
	

	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public NotificadorPoolException ()
	{
		this.mensaje = "Ha ocurrido un error en elnotificador del pool de conexiones.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	

}
