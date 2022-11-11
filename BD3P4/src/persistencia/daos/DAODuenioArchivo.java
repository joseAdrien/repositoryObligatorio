package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import logica.Duenio;
import logica.excepciones.PersistenciaException;
import logica.excepciones.noExisteDuenioException;
import logica.valueObjects.VODuenio;
import persistencia.poolConexiones.ConexionArchivo;
import persistencia.poolConexiones.IConexion;

public class DAODuenioArchivo implements IDAODuenios {

	@Override
	//probado ok
	public boolean member(IConexion con, int ced) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean existeCedula = false;
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Duenio-" + ced + ".txt";

				
		File archivo = new File(nomArch);
		if (archivo.exists()) {
			existeCedula = true;
		}
		return existeCedula;

	}

	@Override
	//probado ok
	public void insert(IConexion con, Duenio Due) throws PersistenciaException {
		// TODO Auto-generated method stub
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Duenio-" + Due.getCedula() + ".txt";
		try	{ 
			// Abro el archivo y creo un flujo de comunicaci�n hacia �l
				FileOutputStream f = new FileOutputStream(nomArch);
				ObjectOutputStream o = new ObjectOutputStream(f);
				// Escribo las colecciones en el archivo a trav�s del flujo
				o.writeObject (Due);
				o.close();
				f.close();
		}
		catch (IOException e) { 
			
			throw new PersistenciaException ();
		}

	}

	@Override
	public Duenio find(IConexion con, int ced) throws PersistenciaException {
		// TODO Auto-generated method stub
		Duenio d;
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Duenio-" + ced + ".txt";
		try { 
			 // Abro el archivo y creo un flujo de comunicaci�n hacia �l
				FileInputStream f = new FileInputStream(nomArch);
				ObjectInputStream o = new ObjectInputStream(f);
			// Leo las colecciones en VOColecciones
				d =  (Duenio) o.readObject();
				o.close();
				f.close();
				
				return d;
		}	
		catch (IOException e) { 
				throw new PersistenciaException ();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException ();
		}
	}


	@Override
	public void delete(IConexion con, int ced) throws noExisteDuenioException {
		// TODO Auto-generated method stub
		ConexionArchivo c = (ConexionArchivo)con;
		String nomArch = "Config/Duenio-" + ced + ".txt";
		File f = new File (nomArch);
		f.delete();

	}

	@Override
	public List<VODuenio> listarDuenios(IConexion con) throws PersistenciaException {
		// TODO Auto-generated method stub
		ConexionArchivo c = (ConexionArchivo)con;
		String ruta = "Config/";
		List<VODuenio> lista = new ArrayList<VODuenio>() ;
		try {
		File file = new File (ruta);

	//  Creo el vector que contendra todos los archivos de una ruta especificada.
		  File[] archivos = file.listFiles();
	//  Evaluo si la carpeta especificada contiene archivos.
		  if (archivos != null) {
//		      Recorro el vector el cual tiene almacenado la ruta del archivo a buscar.
		      for (int i = 0; i < archivos.length; i++) {
		          File Arc = archivos[i];  
	              if (archivos[i].getName().startsWith("Duenio")) {
			  //crear duenio con datos del archivo y agregar a lista de VODuenios
	            	  FileInputStream f = new FileInputStream(ruta + archivos[i].getName());
	            	  ObjectInputStream o = new ObjectInputStream(f);
	            	  Duenio d;
	            	  d =  (Duenio) o.readObject();
	            	  VODuenio vo = new VODuenio(d.getCedula(), d.getNombre(), d.getApellido());
	            	  lista.add(vo);
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
	
}

