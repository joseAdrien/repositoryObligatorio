package logicaPersistencia.valueObjects;

//Importación de clases
import java.io.Serializable;

//Clase ValueObject
public class VOMascotaList extends VOMascota implements Serializable {
	
	//Agrego esto automáticamente
	private static final long serialVersionUID = 1L;

	//Atributos
	private int numInscripcion;
	
	//Constructor
	public VOMascotaList (int num, String apo, String ra){
		super(apo, ra);
		this.numInscripcion = num;
	}
			
	//Selectoras-Getters
	public int getNumInscripcion() 
	{
		return numInscripcion;
	}
}
