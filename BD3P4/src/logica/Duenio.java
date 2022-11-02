package logica;


import java.util.List;

import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;
import persistencia.daos.DAOMascotas;
import persistencia.poolConexiones.IConexion;

public class Duenio {

	private long cedula;

	private String nombre;

	private String apellido;
	
    private DAOMascotas secuencia;

	public Duenio(long cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;

	}

	
//agregar mascotas en ese metodo se llama al insback

	public long getCedula() {
		return cedula;
	}

	

	public String getNombre() {
		return nombre;
	}

	

	public String getApellido() {
		return apellido;
	}

	
	
	
	public boolean tieneMascotas(IConexion icon ,int numInsc) {
		//llamar  this.secuencia.kesimo(icon  , numInsc);
		//retornar true o false si devuelve una mascota
		return false;
	}
	
	public int cantidadMascotas(IConexion icon) {
		//llamar a largo de dao mascotas;
		return 0;
	}
	
	public void agregarMascota(IConexion icon,Mascota masc) {
		//llamar insback de DAO MAscotas
	}
	
	public VOMascota obtenerMascota(IConexion icon,int numInsc) {
		//kesima de dao mascotas
		return null;
	}
	
	public List<VOMascotaList> listarMAscotas(){
		//llamar al listarMAscotas de daoduenio;
		return null;
	}
	
	public void borrarMascotas(IConexion icon) {
		//llamar a borrar mascotas del DAO mascotas
		
	}
	
	public int contarMascotas(IConexion icon, String raza) {
		//llamar a contar mascotas de dao mascotas
		return 0;
	}
}
	

	
	
	
	
	
	
	