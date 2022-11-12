package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logica.Mascota;
import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOMascotaList;
import persistencia.poolConexiones.ConexionArchivo;
import persistencia.poolConexiones.IConexion;

public class DAOMAscotasArchivo implements IDAOMascotas,Serializable {

	private static final long serialVersionUID = 1L;
	
	private int cedDuenio;
	
	
	public DAOMAscotasArchivo(int cedula ) {
		this.cedDuenio = cedula;
	}

	@Override
	//TESTEADO OK
	public void insback(IConexion con, Mascota masc)
			throws PersistenciaException {

		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Mascota-" + cedDuenio + ".txt";
		List<VOMascotaList> listaMascota = new ArrayList <VOMascotaList>();
		try { 
			
			File archivo = new File(nomArch);
			if (archivo.exists()) {
				FileInputStream f = new FileInputStream(nomArch);
				ObjectInputStream o = new ObjectInputStream(f);
				listaMascota =  (ArrayList<VOMascotaList>) o.readObject();
				o.close();
				f.close();
			}
			listaMascota.add(new VOMascotaList((int)masc.getNumlnsc(), masc.getApodo(), masc.getRaza()));
			FileOutputStream g = new FileOutputStream(nomArch);
			ObjectOutputStream j = new ObjectOutputStream(g);
			j.writeObject (listaMascota);
				
			j.close();
			g.close();

				

		}	
		catch (IOException e) { 
				throw new PersistenciaException ();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException ();
		}

	}
	
	
	

	@Override
	//TESTEADO OK
	public int largo(IConexion con)
			throws PersistenciaException {
		int largo = 0;
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Mascota-" + cedDuenio + ".txt";
		List<VOMascotaList> listaMascota = new ArrayList <VOMascotaList>();
		try { 
				File archivo = new File(nomArch);
				if (archivo.exists()) {
					FileInputStream f = new FileInputStream(nomArch);
					ObjectInputStream o = new ObjectInputStream(f);
	
					listaMascota =  (ArrayList<VOMascotaList>) o.readObject();
					o.close();
					f.close();
					
					largo = listaMascota.size();
				}
				
			return largo;
		}	
		catch (IOException e) { 
				throw new PersistenciaException ();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException ();
		}
	}

	@Override
	
	//Testeado OK
	public Mascota kesimo(IConexion con, int numInsc) throws  PersistenciaException {
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Mascota-" + cedDuenio + ".txt";
		List<VOMascotaList> listaMascota = new ArrayList <VOMascotaList>();
		Mascota m = null;
		int i = 0;
		try { 
			File archivo = new File(nomArch);
			if (archivo.exists()) {
				FileInputStream f = new FileInputStream(nomArch);
				ObjectInputStream o = new ObjectInputStream(f);
				listaMascota =  (ArrayList<VOMascotaList>) o.readObject();
				o.close();
				f.close();

				while(m == null && i < listaMascota.size()) {
					VOMascotaList voMmascota = listaMascota.get(i);
					if(voMmascota.getNumInscripcion()== numInsc) {
						m = new Mascota(numInsc,voMmascota.getApodo(),voMmascota.getRaza()) ;
					}
					i = i+1;
				}
			}
			return m;

		}	
		catch (IOException e) { 
				throw new PersistenciaException ();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException ();
		}
	}

	@Override
	//TESTEADO OK
	public List<VOMascotaList> listarMascotas(IConexion con) throws  PersistenciaException {
		// TODO Auto-generated method stub
		ConexionArchivo c = (ConexionArchivo)con;
		String ruta = "Config/";
		List<VOMascotaList> lista = new ArrayList<VOMascotaList>() ;
		try {
		File file = new File (ruta);

		  File[] archivos = file.listFiles();
		  if (archivos != null) {
			  String nombreBuscado =  "Mascota-"+ cedDuenio+ ".txt";
		      for (int i = 0; i < archivos.length; i++) {
		          File Arc = archivos[i];
	              if (archivos[i].getName().equals(nombreBuscado.toString())){
	            	  FileInputStream f = new FileInputStream(ruta + archivos[i].getName());
	            	  ObjectInputStream o = new ObjectInputStream(f);
	            	  
	            	  lista =  (List<VOMascotaList>) o.readObject();
	  					o.close();
	  					f.close();
	              	}
	              
		      	}
	              
		  }    
		} 		
		catch (IOException e) { 
			throw new PersistenciaException ();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException ();
		}
		  return lista;
	}

	@Override
	
	//Testeado ok
	public void borrarMascotas(IConexion con) throws ConectionException {
			ConexionArchivo c = (ConexionArchivo)con;
			String nomArch = "Config/Mascota-" + cedDuenio + ".txt";
			File f = new File (nomArch);
			f.delete();
	}

	@Override
	//Testeado ok
	public int contarMascotas(IConexion con, String raza) throws PersistenciaException {
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Mascota-" + cedDuenio + ".txt";
		List<VOMascotaList> listaMascota = new ArrayList <VOMascotaList>();
		int cantMascotasRaza = 0;
		int i = 0;
		try { 
			File archivo = new File(nomArch);
			if (archivo.exists()) {
				FileInputStream f = new FileInputStream(nomArch);
				ObjectInputStream o = new ObjectInputStream(f);
				listaMascota =  (ArrayList<VOMascotaList>) o.readObject();
				o.close();
				f.close();
				while(i < listaMascota.size()) {
					VOMascotaList voMmascota = listaMascota.get(i);
					if( voMmascota.getRaza().toString().equals(raza.toString())) {
						cantMascotasRaza = cantMascotasRaza +1;
					}
					i = i+1;
				}
			}
			return cantMascotasRaza;

		}	
		catch (IOException e) { 
				throw new PersistenciaException ();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException ();
		}
	}

}