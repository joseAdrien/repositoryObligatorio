package logica.excepciones;

import java.io.Serializable;

public class nuevoDuenioException extends Exception implements Serializable{
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public nuevoDuenioException ()
	{
		this.mensaje = "Ya existe el Dueño.";
	}
	public String getMensaje () 
	{
		return mensaje;
	}	
}
