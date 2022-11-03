package logica;


import java.io.IOException;
import java.util.List;

import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.PropertiesException;
import logica.excepciones.inscripcionException;
import logica.excepciones.noExisteMascotaException;
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

	
	
	
	public boolean tieneMascotas(IConexion icon ,int numInsc) throws PersistenciaException  {
		//llamar  this.secuencia.kesimo(icon  , numInsc);
		//retornar true o false si devuelve una mascota
		boolean tiene = false;
		Mascota aux = null;
		
		try {
			aux = secuencia.kesimo(icon, numInsc);
			if (aux != null)
				tiene = true;
			
		} catch (noExisteMascotaException e) {
			throw new PersistenciaException();
		}
		
		
		return tiene;
	}
	
	public int cantidadMascotas(IConexion icon) throws PersistenciaException{
		//llamar a largo de dao mascotas;
		
		int aux =0;
		
		try {
			aux = secuencia.largo(icon);
		} catch (ClassNotFoundException | IOException | ConectionException e) {
			throw new PersistenciaException();
		}
		return aux;
	}
	
	public void agregarMascota(IConexion icon,Mascota masc) throws PersistenciaException{
		//llamar insback de DAO MAscotas
		
		try {
			secuencia.insback(icon, masc);
		} catch (inscripcionException | ConectionException | PropertiesException e) {
			throw new PersistenciaException();
		}
	}
	
	@SuppressWarnings("null")
	public VOMascota obtenerMascota(IConexion icon,int numInsc) throws PersistenciaException {
		//kesima de dao mascotas
		
		Mascota aux = null;
		VOMascota aux2 = null;
		
		try {
			aux = secuencia.kesimo(icon, numInsc);
			aux2 = new VOMascota(aux.getApodo(),aux.getRaza());
			
		} catch (noExisteMascotaException e) {
			throw new PersistenciaException();
		}
		
		return aux2;
	}
	//FEDE deberia pasarle el id del duenio
	public List<VOMascotaList> listarMAscotas(IConexion icon) throws PersistenciaException{
		//llamar al listarMAscotas de daoduenio;
		List<VOMascotaList> lista = null ;
		try {
			lista = secuencia.listarMascotas(icon);
		} catch (noExisteMascotaException e) {
			throw new PersistenciaException();
		}
		return lista;
	}
	
	public void borrarMascotas(IConexion icon) throws PersistenciaException {
		//llamar a borrar mascotas del DAO mascotas
		
		try {
			secuencia.borrarMascotas(icon);
		} catch (ConectionException e) {
			throw new PersistenciaException();
		}
		
	}
	
	public int contarMascotas(IConexion icon, String raza) throws PersistenciaException {
		//llamar a contar mascotas de dao mascotas
		int aux = 0;
		try {
			aux = secuencia.contarMascotas(icon, raza);
		} catch (ConectionException e) {
			throw new PersistenciaException();
		}
		return aux;
	}
}
	
	