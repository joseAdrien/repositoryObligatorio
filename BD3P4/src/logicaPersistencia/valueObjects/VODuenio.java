package logicaPersistencia.valueObjects;

//Importación de clases
import java.io.Serializable;

//Clase ValueObject 
public class VODuenio implements Serializable {
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;

	//Atributos
	private int cedula;
	private String nombre;
	private String apellido;
	
	//Constructor
	public VODuenio (int ced, String nom, String ape)
	{
		this.cedula = ced;
		this.nombre = nom;
		this.apellido = ape;
	}
			
	//Selectoras-Getters
	public int getCedula() 
	{
		return cedula;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public String getApellido() 
	{
		return apellido;
	}
}
