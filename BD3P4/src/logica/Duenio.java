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

	
	
	
	public boolean tieneMascotas(IConexion icon ,int numInsc) throws Exception {
		//llamar  this.secuencia.kesimo(icon  , numInsc);
		//retornar true o false si devuelve una mascota
		boolean tiene = false;
		Mascota aux = null;
		
		aux = secuencia.kesimo(icon, numInsc);
		if (aux != null)
			tiene = true;
		
		return tiene;
	}
	
	public int cantidadMascotas(IConexion icon) throws Exception{
		//llamar a largo de dao mascotas;
		
		int aux =0;
		
		aux = secuencia.largo(icon);
		return aux;
	}
	
	public void agregarMascota(IConexion icon,Mascota masc) throws Exception{
		//llamar insback de DAO MAscotas
		
		secuencia.insback(icon, masc);
	}
	
	@SuppressWarnings("null")
	public VOMascota obtenerMascota(IConexion icon,int numInsc) throws Exception {
		//kesima de dao mascotas
		
		Mascota aux = null;
		VOMascota aux2 = null;
		
		aux = secuencia.kesimo(icon, numInsc);
		aux2.setRaza(aux.getRaza());
		aux2.setApodo(aux.getApodo());
		return aux2;
	}
	//FEDE deberia pasarle el id del duenio
	public List<VOMascotaList> listarMAscotas(IConexion icon) throws Exception{
		//llamar al listarMAscotas de daoduenio;
		List<VOMascotaList> lista = null ;
		lista = secuencia.listarMascotas(icon);
		return lista;
	}
	
	public void borrarMascotas(IConexion icon) throws Exception {
		//llamar a borrar mascotas del DAO mascotas
		
		secuencia.borrarMascotas(icon);
		
	}
	
	public int contarMascotas(IConexion icon, String raza) throws Exception {
		//llamar a contar mascotas de dao mascotas
		int aux = 0;
		aux = secuencia.contarMascotas(icon, raza);
		return aux;
	}
}
	
	