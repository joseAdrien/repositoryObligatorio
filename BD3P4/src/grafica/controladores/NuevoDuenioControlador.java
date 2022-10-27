package grafica.controladores;

//Importación de clases
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Properties;

import grafica.ventanas.nuevoDuenioVentana;
import logica.IFachada;
import logica.excepciones.ConectionException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;

public class NuevoDuenioControlador {

	//////////////////////////////////////////Ejemplo
	//Atributos
	private IFachada ifachada;
	private nuevoDuenioVentana vent;

	public NuevoDuenioControlador(nuevoDuenioVentana vent)throws SQLException, ConectionException, FileNotFoundException, NotBoundException, IOException
	{
		//Guardo referencia a mi ventana
		this.vent=vent;
		//Lookup del servidor
		try
		{
			//Obtengo IP y N° de puerto del archivo del configuración
			Properties p = new Properties();
			String nomArch = "Config/config.properties";
			p.load (new FileInputStream(nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + ip + ":" + puerto + "/Fachada";

			//Accedo remotamente al objeto publicado en el servidor
			ifachada = (IFachada) Naming.lookup(ruta);

		}
		catch (RemoteException e) //Si ocurren problemas de red
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

	// 4 - Guardar Cambios
	public void guardarNuevoDuenioCont(int cedula, String nombre, String apellido ) throws SQLException, nuevoDuenioException, RemoteException, ConectionException {
		try{
			VODuenio duenio = new VODuenio(cedula, nombre, apellido);
			ifachada.nuevoDuenio(duenio);
		}catch (nuevoDuenioException e){
			throw new nuevoDuenioException();
		}catch (SQLException e){
			throw new ConectionException();
		}catch (ConectionException e) {
			throw new ConectionException();
		}
	}
}


