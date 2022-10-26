package logicaPersistencia.excepciones;

public class PropertiesException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public PropertiesException ()
	{
		this.mensaje = "Ha ocurrido un error en las propiedades de la conexión.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
