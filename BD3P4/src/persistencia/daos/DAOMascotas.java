package persistencia.daos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import logica.Mascota;
import logica.excepciones.ConectionException;
import logica.excepciones.PropertiesException;
import logica.excepciones.inscripcionException;
import logica.excepciones.noExisteMascotaException;
import logica.valueObjects.VOMascotaList;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;

public class DAOMascotas {
	
    //Agrega la mascota a la BD
	public void insback (IConexion con,Mascota masc) throws inscripcionException, ConectionException, PropertiesException
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
			insback.executeUpdate ();
			insback.close ();

		}
		catch (SQLException e) {
			throw new inscripcionException();
		}
	}
	
	public int largo (IConexion con) throws FileNotFoundException, IOException, ClassNotFoundException, ConectionException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.largo ();
			PreparedStatement pstmt1 = conexion.prepareStatement (query);
			ResultSet large = pstmt1.executeQuery ();
			int larg = large.getInt(1);
			large.close();
			pstmt1.close ();
			return larg;
		}
		catch (SQLException e) {
			throw new ConectionException();
		}
	}
	
	public Mascota find (IConexion con, int numInsc) throws noExisteMascotaException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Mascota mas = null;
			Consultas consultas = new Consultas ();
			String query = consultas.kmascota ();
			PreparedStatement kesimo = conexion.prepareStatement (query);
			kesimo.setInt(1, numInsc);
			ResultSet rs;
			rs = kesimo.executeQuery();
			if (rs.next()) {
				int ni = rs.getInt(1);
				String apodo = rs.getString(2);
				String raza = rs.getString(3);
				mas.setNumlnsc(numInsc);
				mas.setApodo(apodo);
				mas.setRaza(raza);
			};
			
			rs.close();
			kesimo.close();
			return mas;
		}
		catch (SQLException e) {
			throw new noExisteMascotaException();
		}
	}
	
	public List<VOMascotaList> listarMascotas (IConexion con) throws noExisteMascotaException 
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			
			List<VOMascotaList> lista = null ;
			Consultas consultas = new Consultas ();
			String query = consultas.listarMascotas ();
			PreparedStatement LM = conexion.prepareStatement (query);
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
			throw new noExisteMascotaException();
		}
	}
	
	public void borrarMascotas (IConexion con) throws  ConectionException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.deleteMascotas ();
			PreparedStatement delete = conexion.prepareStatement (query);
			delete.executeUpdate();
			delete.close();
		}
		catch (SQLException e) {
			throw new ConectionException();
		}
	}
	
	public int contarMascotas (IConexion con, String raza) throws ConectionException
	{
		try
		{
			Connection conexion = ((Conexion)con).getConexion();
			Consultas consultas = new Consultas ();
			String query = consultas.contarMascotas ();
			PreparedStatement pstmt1 = conexion.prepareStatement (query);
			pstmt1.setString (1, raza);
			ResultSet pstmt2 = pstmt1.executeQuery ();
			int cont = pstmt2.getInt(1);
			pstmt2.close ();
			return cont;
		}
		catch (SQLException e) {
			throw new ConectionException();
		}
	}

}


