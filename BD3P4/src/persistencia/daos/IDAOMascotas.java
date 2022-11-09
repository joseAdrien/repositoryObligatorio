package persistencia.daos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import logica.Mascota;
import logica.excepciones.ConectionException;
import logica.excepciones.PropertiesException;
import logica.excepciones.inscripcionException;
import logica.excepciones.noExisteMascotaException;
import logica.valueObjects.VOMascotaList;
import persistencia.poolConexiones.IConexion;

public interface IDAOMascotas {

	//Agrega la mascota a la BD
	void insback(IConexion con, Mascota masc) throws inscripcionException, ConectionException, PropertiesException;

	int largo(IConexion con) throws FileNotFoundException, IOException, ClassNotFoundException, ConectionException;

	Mascota kesimo(IConexion con, int numInsc) throws noExisteMascotaException;

	List<VOMascotaList> listarMascotas(IConexion con) throws noExisteMascotaException;

	void borrarMascotas(IConexion con) throws ConectionException;

	int contarMascotas(IConexion con, String raza) throws ConectionException;

}