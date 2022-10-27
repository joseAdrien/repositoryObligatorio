package logica.excepciones;

public class noExisteDuenioException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public noExisteDuenioException ()
	{
		this.mensaje = "No existe el Dueño.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
