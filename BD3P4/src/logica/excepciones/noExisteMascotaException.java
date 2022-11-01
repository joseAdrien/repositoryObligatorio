package logica.excepciones;

public class noExisteMascotaException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
		
	private String mensaje;
		
	public noExisteMascotaException ()
	{
		this.mensaje = "No existe la mascota.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}


