package persistencia.daos;

import java.util.List;

import logica.Duenio;
import logica.excepciones.noExisteDuenioException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import persistencia.poolConexiones.IConexion;

public class DAODuenioArchivo implements IDAODuenios {

	@Override
	public boolean member(IConexion con, int ced) throws noExisteDuenioException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insert(IConexion con, Duenio Due) throws nuevoDuenioException {
		// TODO Auto-generated method stub

	}

	@Override
	public Duenio find(IConexion con, int ced) throws noExisteDuenioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(IConexion con, int ced) throws noExisteDuenioException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VODuenio> listarDuenios(IConexion con) throws noExisteDuenioException {
		// TODO Auto-generated method stub
		return null;
	}

}
