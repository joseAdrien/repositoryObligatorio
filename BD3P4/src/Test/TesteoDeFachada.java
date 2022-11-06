package Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import logica.Fachada;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VODuenio;

public class TesteoDeFachada {
	
static IFachada fachada;

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
       try {
    	   
    	   
    	 //Obtengo IP y N° de puerto del archivo del configuración
			Properties p = new Properties();
			String nomArch = "Config/config.properties";
			p.load (new FileInputStream(nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + ip + ":" + puerto + "/Fachada";

			//Accedo remotamente al objeto publicado en el servidor
			fachada =  (IFachada)Naming.lookup(ruta);



			List<VODuenio> lista = fachada.listarDuenios();
			System.out.println(lista.size());
		
		
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (PersistenciaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}

}
