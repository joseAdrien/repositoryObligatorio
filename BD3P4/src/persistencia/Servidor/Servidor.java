//Package
package persistencia.Servidor;

//Importación de clases
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.swing.JOptionPane;

import logica.Fachada;
import logica.excepciones.PersistenciaException;
import logica.excepciones.PropertiesException;

//Clase Servidor
public class Servidor {

	private static Fachada CPLogica;

	public static void main(String[] args) throws RemoteException, MalformedURLException, PropertiesException, PersistenciaException {

		// TODO Auto-generated method stub

		try {
			System.out.println(System.getProperty("user.dir"));
			// Obtengo IP y N° de puerto del archivo del configuración
			Properties p = new Properties();
			String nomArchi = "Config/config.properties";
			p.load(new FileInputStream(nomArchi));
			InetAddress iplocal = InetAddress.getLocalHost();
			String ip = iplocal.getHostAddress();
			//String ip = p.getProperty("ipServidor");
			p.setProperty("ipServidor", ip);
			// p.store(new FileWriter ("Config/config.properties"),"Datos Conexión");
			String puerto = p.getProperty("puertoServidor");
			int port = Integer.parseInt(puerto);
			System.out.println(ip);
			// Ejecuto el rmiregistry
			LocateRegistry.createRegistry(port);

			// Publico el objeto remoto en IP y N° de puertos obtenidos
			String ruta = "//" + ip + ":" + puerto + "/Fachada";
			////192.168.1.3:5004/Fachada
			CPLogica = new Fachada();

			Naming.rebind(ruta, CPLogica);
			System.out.println("Servidor iniciado");

		} catch (RemoteException e) // Si ocurren problemas de red
		{
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) // Si la ruta es incorrecta
		{
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) // Si no encuentra el archivo de configuración
		{
			System.out.println(e.getMessage());
		} catch (IOException e) // Si ocurre cualquier otro error de E/S
		{
			System.out.println(e.getMessage());
		}
	}
}
