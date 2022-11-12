package logica;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.PropertiesException;
import logica.excepciones.inscripcionException;
import logica.excepciones.noExisteMascotaException;
import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;
import persistencia.abstracta.IFabricaAbstracta;
import persistencia.daos.IDAOMascotas;
import persistencia.poolConexiones.IConexion;

public class Duenio implements Serializable{

	private static final long serialVersionUID = 1L;

	private int cedula;

	private String nombre;

	private String apellido;
	
    private IDAOMascotas secuencia;
    

	public Duenio(int cedula, String nombre, String apellido) throws PersistenciaException  {
		super();
		
		

		Properties p = new Properties();
		String nomArchi = "Config/config.properties";
		String nombreFabrica = "";
		try {
			p.load (new FileInputStream (nomArchi));
			nombreFabrica= p.getProperty("fabrica");
			IFabricaAbstracta fabrica = (IFabricaAbstracta)Class.forName(nombreFabrica).newInstance();
			this.cedula = cedula;
			this.nombre = nombre;
			this.apellido = apellido;
			secuencia = fabrica.crearIDAOMAscotas(cedula);
		} catch (IOException e) {
			throw new PersistenciaException();
		} catch (InstantiationException e) {
			throw new PersistenciaException();
		} catch (IllegalAccessException e) {
			throw new PersistenciaException();
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException();
		}
		
		
		

        
		
		
	}

	
//agregar mascotas en ese metodo se llama al insback

	public  int getCedula() {
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
		
		aux = secuencia.kesimo(icon, numInsc);
		if (aux != null)
			tiene = true;
		
		
		return tiene;
	}
	
	public int cantidadMascotas(IConexion icon) throws PersistenciaException{
		//llamar a largo de dao mascotas;
		
		int aux =0;
		aux = secuencia.largo(icon);
		return aux;
	}
	
	public void agregarMascota(IConexion icon,Mascota masc) throws PersistenciaException{
		//llamar insback de DAO MAscotas
		
		secuencia.insback(icon, masc);
	}
	
	@SuppressWarnings("null")
	public VOMascota obtenerMascota(IConexion icon,int numInsc) throws PersistenciaException {
		//kesima de dao mascotas
		
		Mascota aux = null;
		VOMascota aux2 = null;
		
		aux = secuencia.kesimo(icon, numInsc);
		if(aux != null) { 
			aux2 = new VOMascota(aux.getApodo(),aux.getRaza());
		}
		
		return aux2;
	}

	public List<VOMascotaList> listarMAscotas(IConexion icon) throws PersistenciaException{
		//llamar al listarMAscotas de daoduenio;
		List<VOMascotaList> lista = null ;
		lista = secuencia.listarMascotas(icon);
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
		aux = secuencia.contarMascotas(icon, raza);
		return aux;
	}
}
	
	