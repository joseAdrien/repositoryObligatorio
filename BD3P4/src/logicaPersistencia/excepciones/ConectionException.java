package logicaPersistencia.excepciones;

public class ConectionException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public ConectionException ()
	{
		this.mensaje = "Ocurrió un error al intentar conectarse a la base de datos.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
