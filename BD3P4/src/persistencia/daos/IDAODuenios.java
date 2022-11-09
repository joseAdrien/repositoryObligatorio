package persistencia.daos;

import java.util.List;

import logica.Duenio;
import logica.excepciones.noExisteDuenioException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import persistencia.poolConexiones.IConexion;

public interface IDAODuenios {

	boolean member(IConexion con, int ced) throws noExisteDuenioException;

	void insert(IConexion con, Duenio Due) throws nuevoDuenioException;

	Duenio find(IConexion con, int ced) throws noExisteDuenioException;

	void delete(IConexion con, int ced) throws noExisteDuenioException;

	List<VODuenio> listarDuenios(IConexion con) throws noExisteDuenioException;

}