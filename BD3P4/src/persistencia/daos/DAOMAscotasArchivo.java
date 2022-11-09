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

public class DAOMAscotasArchivo implements IDAOMascotas {

	@Override
	public void insback(IConexion con, Mascota masc)
			throws inscripcionException, ConectionException, PropertiesException {
		// TODO Auto-generated method stub

	}

	@Override
	public int largo(IConexion con)
			throws FileNotFoundException, IOException, ClassNotFoundException, ConectionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mascota kesimo(IConexion con, int numInsc) throws noExisteMascotaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VOMascotaList> listarMascotas(IConexion con) throws noExisteMascotaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarMascotas(IConexion con) throws ConectionException {
		// TODO Auto-generated method stub

	}

	@Override
	public int contarMascotas(IConexion con, String raza) throws ConectionException {
		// TODO Auto-generated method stub
		return 0;
	}

}
