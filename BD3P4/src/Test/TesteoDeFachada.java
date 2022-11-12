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
import logica.valueObjects.VOMascotaList;

public class TesteoDeFachada {
	
static IFachada fachada;

	public static void main(String[] args) throws RemoteException, ConectionException {
		// TODO Auto-generated method stub
       try {
    	   // ********************* para RMI************************
    	   
    	 
			Properties p = new Properties();
			String nomArch = "Config/config.properties";
			p.load (new FileInputStream(nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + ip + ":" + puerto + "/Fachada";
			//fachada =  (IFachada)Naming.lookup(ruta);
			
			//Prueba Listar Duenios//
			//List<VODuenio> lista = fachada.listarDuenios();
			//System.out.println(lista.size());
			
			//Prueba Nueva mascota*/
			//fachada.nuevaMascota(1234567, new VOMascota("manchita","granDanes"));
			//fachada.nuevoDuenio(new VODuenio(34444850,"Jose","Adrien"));
			
//************************para pruebas directas***************8//
    	    fachada = new FachadaForTest();
			//List<VODuenio> lista = fachada.listarDuenios();
			//System.out.println(lista.size());
		    //fachada.contarMascotas(32526363, "dasd");
		
    	    //fachada.listarMascotasDuenio(34444850);
    	    //VODuenio duenio = new VODuenio(1234567,"juan","perez");
    	    //fachada.nuevoDuenio(duenio);
    	    //fachada.nuevaMascota(123456, new VOMascota("pelotita","guardian"));
    	    //int cantidad = fachada.contarMascotas(123456, "guardian");
    	    //VOMascota m = fachada.obtenerMascota(12345679, 7);
    	   // List<VOMascotaList> l = fachada.listarMascotasDuenio(1234567);
    	    fachada.borrarDuenioMascotas(1234567);
int a = 0;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} catch (PersistenciaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	}
}


