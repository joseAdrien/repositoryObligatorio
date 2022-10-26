package logicaPersistencia.excepciones;

public class NoRelacionDueInsException extends Exception{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public NoRelacionDueInsException ()
	{
		this.mensaje = "La inscripción no pertenece a ese Dueño.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
