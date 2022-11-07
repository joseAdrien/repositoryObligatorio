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
import logica.FachadaForTest;
import logica.IFachada;
import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VODuenio;
import logica.valueObjects.VOMascota;

public class TesteoDeFachada {
	
static IFachada fachada;

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
       try {
    	   // ********************* para RMI************************
    	   
    	 
			Properties p = new Properties();
			String nomArch = "Config/config.properties";
			p.load (new FileInputStream(nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + ip + ":" + puerto + "/Fachada";
			fachada =  (IFachada)Naming.lookup(ruta);
			
			//Prueba Listar Duenios//
			List<VODuenio> lista = fachada.listarDuenios();
			System.out.println(lista.size());
			
			//Prueba Nueva mascota*/
			//fachada.nuevaMascota(1234567, new VOMascota("manchita","granDanes"));
			//fachada.nuevoDuenio(new VODuenio(34444850,"Jose","Adrien"));
//************************para pruebas directas***************8//
    	    fachada = new FachadaForTest();
			//List<VODuenio> lista = fachada.listarDuenios();
			//System.out.println(lista.size());
		    //fachada.nuevoDuenio(new VODuenio(34444850,"Jose","Adrien"));
		
    	    //fachada.listarMascotasDuenio(34444850);
    	    fachada.contarMascotas(1234567, "granDanes");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} catch (PersistenciaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}

}
