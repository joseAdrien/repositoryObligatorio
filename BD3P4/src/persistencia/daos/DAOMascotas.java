package persistencia.daos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Mascota;
import logica.excepciones.ConectionException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.PropertiesException;
import logica.excepciones.inscripcionException;
import logica.excepciones.noExisteMascotaException;
import logica.valueObjects.VOMascotaList;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;

public class DAOMascotas implements IDAOMascotas {
	
private int cedDuenio;
		
		
	public DAOMascotas(int cedula) {
		this.cedDuenio = cedula;
	}
	
    //Agrega la mascota a la BD
	@Override
	public void insback (IConexion con,Mascota masc) throws   PersistenciaException
	{
		try
		{

			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.insback ();
			PreparedStatement insback = conexion.prepareStatement (query);
			insback.setLong (1, masc.getNumlnsc());
			insback.setString (2, masc.getApodo());
			insback.setString (3, masc.getRaza());
			insback.setInt(4, cedDuenio);
			insback.executeUpdate ();
			insback.close ();

		}
		catch (SQLException e) {
			throw new PersistenciaException();
		}
	}
	
	@Override
	public int largo (IConexion con) throws PersistenciaException
	{
		try
		{
			int cantidad = 0;
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.largo ();
			PreparedStatement pstmt1 = conexion.prepareStatement (query);
			pstmt1.setInt(1, cedDuenio);
			ResultSet large = pstmt1.executeQuery ();
			if(large.next()) {
				cantidad = large.getInt(1);
			}
			large.close();
			pstmt1.close ();
			return cantidad;
		}
		catch (SQLException e) {
			throw new PersistenciaException();
		}
	}
	
	@Override
	public Mascota kesimo (IConexion con, int numInsc) throws PersistenciaException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Mascota mas = null;
			Consultas consultas = new Consultas ();
			String query = consultas.kmascota ();
			PreparedStatement kesimo = conexion.prepareStatement (query);
			kesimo.setInt(1, cedDuenio);
			kesimo.setInt(2, numInsc);
			ResultSet rs;
			rs = kesimo.executeQuery();
			if (rs.next()) {
				String apodo = rs.getString(2);
				String raza = rs.getString(3);
				mas = new Mascota(numInsc,apodo,raza);
				
			};
			
			rs.close();
			kesimo.close();
			return mas;
		}
		catch (SQLException e) {
			throw new PersistenciaException();
		}
	}
	
	@Override
	public List<VOMascotaList> listarMascotas (IConexion con) throws PersistenciaException 
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			
			List<VOMascotaList> lista = new ArrayList<VOMascotaList>() ;
			Consultas consultas = new Consultas ();
			String query = consultas.listarMascotas ();
			PreparedStatement LM = conexion.prepareStatement (query);
			LM.setInt(1, cedDuenio);
			ResultSet rs;
			rs = LM.executeQuery();
			while (rs.next()) {
				
				int nI = rs.getInt(1);
				String apodo = rs.getString(2);
				String raza = rs.getString(3);
				lista.add(new VOMascotaList(nI, apodo, raza));
				
			};
			rs.close();
			LM.close();
			return lista;
		}
		catch (SQLException e) {
			throw new PersistenciaException();
		}
	}
	
	@Override
	public void borrarMascotas (IConexion con) throws  ConectionException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.deleteMascotas ();
			PreparedStatement delete = conexion.prepareStatement (query);
			delete.setInt(1, cedDuenio);
			delete.executeUpdate();
			delete.close();
		}
		catch (SQLException e) {
			throw new ConectionException();
		}
	}
	
	@Override
	public int contarMascotas (IConexion con, String raza) throws PersistenciaException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.contarMascotas ();
			PreparedStatement pstmt1 = conexion.prepareStatement (query);
			pstmt1.setInt(1, cedDuenio);
			pstmt1.setString (2, raza);
			ResultSet pstmt2 = pstmt1.executeQuery ();
			int cont = 0;
			while(pstmt2.next()) {
				cont = pstmt2.getInt(1);
			}
			pstmt2.close ();
			return cont;
		}
		catch (SQLException e) {
			throw new PersistenciaException();
		}
	}

}


