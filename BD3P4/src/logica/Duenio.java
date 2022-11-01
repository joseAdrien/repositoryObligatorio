package logica;





public class Duenio {

	private long cedula;

	private String nombre;

	private String apellido;
	
	///////// secuencia : DAOMascotas//////////

	public Duenio(long cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Duenio() {
		// TODO Auto-generated constructor stub
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
	

	
	
	
	
	
	
	