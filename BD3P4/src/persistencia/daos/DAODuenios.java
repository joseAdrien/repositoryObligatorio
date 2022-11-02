package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import logica.Duenio;
import logica.excepciones.noExisteDuenioException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;

public class DAODuenios { 
	
	public boolean member (IConexion con, int ced) throws noExisteDuenioException 
	{
		boolean existeCedula = false;
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.existsCedula ();
			PreparedStatement pstmt1 = conexion.prepareStatement (query);
			pstmt1.setInt (1, ced);
			ResultSet rs = pstmt1.executeQuery ();
			if (rs.next ()) {
				existeCedula = true;
				rs.close ();
				pstmt1.close ();
			}
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
		return existeCedula;
	}
	
	public void insert (IConexion con, Duenio Due) throws nuevoDuenioException 
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String insert = consultas.insertDuenio ();
			PreparedStatement pstmt2 = conexion.prepareStatement (insert);
			pstmt2.setLong (1, Due.getCedula());
			pstmt2.setString (2, Due.getNombre());
			pstmt2.setString (3, Due.getApellido());
			pstmt2.executeUpdate ();
			pstmt2.close ();
		}
		catch (SQLException e) {
			throw new nuevoDuenioException();
		}
	}
	
	public Duenio find (IConexion con, int ced) throws noExisteDuenioException 
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Duenio due =null;
			Consultas consultas = new Consultas ();
			String query = consultas.findDuenio ();
			PreparedStatement find = conexion.prepareStatement (query);
			find.setInt(1, ced);
			ResultSet rs;
			rs = find.executeQuery();
			if (rs.next()) {
				int cedu = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				due = new Duenio(cedu, nombre, apellido);
			};
			
			rs.close();
			find.close();
			return due;
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
	}
	
	public void delete (IConexion con, int ced) throws noExisteDuenioException 
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.deleteDuenio ();
			PreparedStatement delete = conexion.prepareStatement (query);
			delete.setInt(1, ced);
			delete.executeUpdate();
			delete.close();
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
	}
	
	public List<VODuenio> listarDuenios (IConexion con) throws noExisteDuenioException 
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			
			List<VODuenio> lista = null;
			Consultas consultas = new Consultas ();
			String query = consultas.listarDuenios ();
			PreparedStatement LD = conexion.prepareStatement (query);
			ResultSet rs;
			rs = LD.executeQuery();
			while (rs.next()) {
				int cedu = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				VODuenio due = new VODuenio(cedu, nombre, apellido);
				lista.add(due);
			};
			rs.close();
			LD.close();
			return lista;
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
	}
}



