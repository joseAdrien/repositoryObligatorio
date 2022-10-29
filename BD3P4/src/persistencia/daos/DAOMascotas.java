package persistencia.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import logica.Duenio;
import logica.Mascota;
import logica.excepciones.ConectionException;
import logica.excepciones.PropertiesException;
import logica.excepciones.inscripcionException;
import logica.excepciones.noExisteDuenioException;
import logica.valueObjects.VODuenio;
import logica.valueObjects.VOMascota;
import logica.valueObjects.VOMascotaList;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.Conexion;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.PoolConexiones;

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
			insback.setInt (1, masc.getNumeroInscripcion());
			insback.setString (2, masc.getApodo());
			insback.setDouble (3, masc.getRaza());
			insback.executeUpdate ();
			insback.close ();

		}
		catch (SQLException e) {
			throw new inscripcionException();
		}
	}
	
	public int largo () throws FileNotFoundException, IOException, ClassNotFoundException, ConectionException
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
			String query = consultas.largo ();
			PreparedStatement pstmt1 = con.prepareStatement (query);
			ResultSet large = pstmt1.executeQuery ();
			int larg = large.getInt(1);
			large.close();
			pstmt1.close ();
			con.close ();
			return larg;
		}
		catch (SQLException e) {
			throw new ConectionException();
		}
	}
	
	public Mascota find (int numInsc) throws FileNotFoundException, IOException, ClassNotFoundException
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
			
			Mascota mas;
			Consultas consultas = new Consultas ();
			String query = consultas.kmascota ();
			PreparedStatement kesimo = con.prepareStatement (query);
			kesimo.setInt(1, numInsc);
			ResultSet rs;
			rs = kesimo.executeQuery();
			if (rs.next()) {
				int ni = rs.getInt(1);
				String apodo = rs.getString(2);
				String raza = rs.getString(3);
			};
			mas = Mascota.setNumeroInscripcion(ni);
			mas = Mascota.setApodo(apodo);
			mas = Mascota.setRaza(raza);
			rs.close();
			kesimo.close();
			con.close ();
			return mas;
		}
		catch (SQLException e) {
			throw new //GENERAR EXCEPCION DE NO EXSITE MASCOTA;
		}
	}
	
	public List<VOMascotaList> listarMascotas () throws FileNotFoundException, IOException, ClassNotFoundException
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
			
			VOMascota VOM;
			List<VOMascotaList> lista = null;
			Consultas consultas = new Consultas ();
			String query = consultas.listarMascotas ();
			PreparedStatement LM = con.prepareStatement (query);
			ResultSet rs;
			rs = LM.executeQuery();
			while (rs.next()) {
				VOM = null;
				int nI = rs.getInt(1);
				String apodo = rs.getString(2);
				String raza = rs.getString(3);
				VOM = Mascota.setNumeroInscripcion(nI);
				VOM = Mascota.setApodo(apodo);
				VOM = MAscota.setRaza(raza);
				lista.add(VOM);
			};
			rs.close();
			LM.close();
			con.close ();
			return lista;
		}
		catch (SQLException e) {
			throw new //noexistemascotaexcepcion CREAR;
		}
	}
	
	public void borrarMascotas () throws FileNotFoundException, IOException, ClassNotFoundException
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
			String query = consultas.deleteMascotas ();
			PreparedStatement delete = con.prepareStatement (query);
			delete.executeUpdate();
			delete.close();
			con.close ();
		}
		catch (SQLException e) {
			throw new //OTRA VEZ LA EXCEPCION QUE FALTA;
		}
	}
	
	public int contarMascotas (String raza) throws FileNotFoundException, IOException, ClassNotFoundException
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
			String query = consultas.contarMascotas ();
			PreparedStatement pstmt1 = con.prepareStatement (query);
			pstmt1.setString (1, raza);
			ResultSet pstmt1 = pstmt1.executeQuery ();
			int cont = pstmt1.getInt(1);
			pstmt1.close ();
			con.close ();
			return cont;
		}
		catch (SQLException e) {
			throw new Exception("No existe la base u ocurrio un problema");
		}
	}

}
