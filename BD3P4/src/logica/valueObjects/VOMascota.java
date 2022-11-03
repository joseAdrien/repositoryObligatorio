package logica.valueObjects;

//Importación de clases
import java.io.Serializable;

//Clase ValueObject 
public class VOMascota implements Serializable {
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;

	//Atributos
	private String apodo;
	private String raza;
	
	//Constructor
	public VOMascota (String apo, String ra)
	{
		this.apodo = apo;
		this.raza = ra;
	}
			
	//Selectoras-Getters
	public String getApodo() 
	{
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}
	
	public String getRaza() 
	{
		return raza;
	}
	
	public void setRaza(String raza) {
		this.raza = raza;
	}

}