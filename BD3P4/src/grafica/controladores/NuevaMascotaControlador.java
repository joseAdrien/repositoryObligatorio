package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//Importación de clases
import java.sql.SQLException;
import java.util.Properties;

import grafica.ventanas.nuevaMascotaVentana;
import logica.IFachada;
import logica.excepciones.ConectionException;
import logica.excepciones.noExisteDuenioException;
import logica.valueObjects.VOMascota;

//Clase 
public class NuevaMascotaControlador {

	//Atributos
	private IFachada ifachada;
	private nuevaMascotaVentana vent;
	
	public NuevaMascotaControlador(nuevaMascotaVentana vent)throws SQLException, ConectionException, NotBoundException, FileNotFoundException, IOException, RemoteException {
		this.vent = vent;
		
		try {
		//Obtengo IP y N° de puerto del archivo del configuración
		Properties p = new Properties();
		String nomArch = "Config/config.properties";
		p.load (new FileInputStream (nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/Fachada";
		
		//Accedo remotamente al objeto publicado en el servidor
		ifachada = (IFachada) Naming.lookup(ruta);
		}catch (RemoteException e) //Si ocurren problemas de red
		{
			throw new RemoteException();
		}
		catch (MalformedURLException e) //Si la ruta es incorrecta
		{
			throw new MalformedURLException(); 
		}
		catch (FileNotFoundException e) //Si no encuentra el archivo de configuración
		{
			throw new FileNotFoundException();
		}
		catch (NotBoundException e) //Si la ruta esta bien, pero el servidor no está activo
		{
			throw new NotBoundException();
		}
		catch (IOException e) //Si ocurre cualquier otro error de E/S
		{
			throw new IOException();
		}
		
	}
	
	public void NuevaMascotaCont(int cedula, String apodo, String raza) throws SQLException, ConectionException, noExisteDuenioException, RemoteException
	{		
		try
		{
			VOMascota mascota = new VOMascota(apodo, raza);
			
			ifachada.nuevaMascota(cedula, mascota);
			
		}catch (noExisteDuenioException e) {
			throw new noExisteDuenioException();
		}catch(SQLException e){
			throw new ConectionException();
		}catch(ConectionException e) {
			throw new ConectionException();
		}
	}
}