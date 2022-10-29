package persistencia.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import logica.Duenio;
import logica.excepciones.noExisteDuenioException;
import logica.excepciones.nuevoDuenioException;
import logica.valueObjects.VODuenio;
import persistencia.consultas.Consultas;

public class DAODuenios { 
	
	public boolean member (int ced) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		boolean existeCedula = false;
		try
		{
			Properties p = new Properties();
			String nomArchi = "Config/config.properties";
			p.load (new FileInputStream (nomArchi));
			String driver = p.getProperty("driver");
			String url = p.getProperty("urlraiz");
			String usuario = p.getProperty("usuario");
			String password = p.getProperty("password");
			
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, usuario, password);
			
			Consultas consultas = new Consultas ();
			String query = consultas.existsCedula ();
			PreparedStatement pstmt1 = con.prepareStatement (query);
			pstmt1.setInt (1, ced);
			ResultSet rs = pstmt1.executeQuery ();
			if (rs.next ())
				existeCedula = true;
				rs.close ();
				pstmt1.close ();
				con.close ();
		}
		catch (SQLException e) {
		throw new noExisteDuenioException();
		return existeCedula;
		}
	}
	
	public void insert (Duenio Due) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try
		{
			Properties p = new Properties();
			String nomArchi = "Config/config.properties";
			p.load (new FileInputStream (nomArchi));
			String driver = p.getProperty("driver");
			String url = p.getProperty("urlraiz");
			String usuario = p.getProperty("usuario");
			String password = p.getProperty("password");
			
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, usuario, password);
			
			Consultas consultas = new Consultas ();
			String insert = consultas.insertDuenio ();
			PreparedStatement pstmt2 =
			con.prepareStatement (insert);
			pstmt2.setInt (1, Due.getCedula());
			pstmt2.setString (2, Due.getNombre());
			pstmt2.setDouble (3, Due.getSueldo());
			pstmt2.executeUpdate ();
			pstmt2.close ();
			con.close ();
		}
		catch (SQLException e) {
			throw new nuevoDuenioException();
		}
	}
	
	public Duenio find (int ced) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try
		{
			Properties p = new Properties();
			String nomArchi = "Config/config.properties";
			p.load (new FileInputStream (nomArchi));
			String driver = p.getProperty("driver");
			String url = p.getProperty("urlraiz");
			String usuario = p.getProperty("usuario");
			String password = p.getProperty("password");
			
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, usuario, password);
			
			Duenio due;
			Consultas consultas = new Consultas ();
			String query = consultas.findDuenio ();
			PreparedStatement find = con.prepareStatement (query);
			find.setInt(1, ced);
			ResultSet rs;
			rs = find.executeQuery();
			if (rs.next()) {
				int cedu = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				due = Duenio.setCedula(cedu);
				due = Duenio.setNombre(nombre);
				due = Duenio.setApellido(apellido);
			};
			
			rs.close();
			find.close();
			con.close ();
			return due;
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
	}
	
	public void delete (int ced) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try
		{
			Properties p = new Properties();
			String nomArchi = "Config/config.properties";
			p.load (new FileInputStream (nomArchi));
			String driver = p.getProperty("driver");
			String url = p.getProperty("urlraiz");
			String usuario = p.getProperty("usuario");
			String password = p.getProperty("password");
			
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, usuario, password);
			
			Consultas consultas = new Consultas ();
			String query = consultas.deleteDuenio ();
			PreparedStatement delete = con.prepareStatement (query);
			delete.setInt(1, ced);
			delete.executeUpdate();
			delete.close();
			con.close ();
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
	}
	
	public List<VODuenio> listarDuenios () throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try
		{
			Properties p = new Properties();
			String nomArchi = "Config/config.properties";
			p.load (new FileInputStream (nomArchi));
			String driver = p.getProperty("driver");
			String url = p.getProperty("urlraiz");
			String usuario = p.getProperty("usuario");
			String password = p.getProperty("password");
			
			/* 1. cargo dinamicamente el driver de MySQL */
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, usuario, password);
			
			VODuenio due;
			List<VODuenio> lista = null;
			Consultas consultas = new Consultas ();
			String query = consultas.listarDuenios ();
			PreparedStatement LD = con.prepareStatement (query);
			ResultSet rs;
			rs = LD.executeQuery();
			while (rs.next()) {
				due = null;
				int cedu = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				due = Duenio.setCedula(cedu);
				due = Duenio.setNombre(nombre);
				due = Duenio.setApellido(apellido);
				lista.add(due);
			};
			rs.close();
			LD.close();
			con.close ();
			return lista;
		}
		catch (SQLException e) {
			throw new noExisteDuenioException();
		}
	}
}



