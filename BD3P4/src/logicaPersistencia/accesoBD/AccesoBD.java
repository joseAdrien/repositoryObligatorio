package logicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.valueObjects.*;

public class AccesoBD {
	
	public static void crearDuenio (Connection con, VODuenio due) throws SQLException {
		
		Consultas cons = new Consultas();
		
		String query = cons.CrearDuenio();
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, due.getCedula());
		pstmt.setString(2, due.getNombre());
		pstmt.setString(3, due.getApellido());
			
		pstmt.executeUpdate();
			
		pstmt.close();
			
	}
	
	public static Boolean existeDuenio (Connection con, int cedula) throws SQLException {
		Consultas cons = new Consultas();
		
		String query = cons.ExisteDuenio();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
		ResultSet rs = pstmt.executeQuery();
		
		Boolean existe = false;
		
		if (rs.next()) { //Devuelve uno o ningun dueño, por eso un if
			existe=true;
		}
		rs.close();
		pstmt.close();
		return existe;
	}
	
	public static void crearMascota (Connection con, int cedula, VOMascota mas) throws SQLException {
		
		Consultas cons = new Consultas();
		
		String query = cons.UltimaMascotaDuenio();
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
			
		ResultSet rs = pstmt.executeQuery();
			
		if(rs.next()) {
			int cant = rs.getInt(1);
			cant ++;
			query = cons.InsertMascota();
				
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cant);
			pstmt.setString(2, mas.getApodo());
			pstmt.setString(3, mas.getRaza());
			pstmt.setInt(4, cedula);
								
			pstmt.executeUpdate();
		}
		rs.close();	
		pstmt.close();
	}
	
	public static void borrarDuenioMascotas (Connection con, int cedula) throws SQLException {
		Consultas cons = new Consultas();
		//Borro las mascotas del dueño
		String query = cons.BorrarMascotasDuenio();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
			
		pstmt.executeUpdate();
			
		//Borro al dueño
		query = cons.BorrarDuenio();
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
			
		pstmt.executeUpdate();
		
		pstmt.close();
	}
	
	
	public static List <VODuenio> listarDuenios (Connection con) throws SQLException{
		List <VODuenio> ListaDuenios = new ArrayList<VODuenio>();
		Consultas cons = new Consultas();
		String query = cons.ListarDuenios();
		
		Statement stmt = con.createStatement();
		ResultSet res =stmt.executeQuery(query);
			
		while (res.next()) {
			int cedula = res.getInt(1);
			String nombre = res.getString(2);
			String apellido = res.getString(3);
			VODuenio duenio = new VODuenio(cedula, nombre, apellido);
			ListaDuenios.add(duenio);
		}
		
		res.close();
		stmt.close();
			
		return ListaDuenios;
		
	}
	
	public static List <VOMascotaList> listarMascotasDuenio (Connection con, int cedula) throws SQLException{
		List <VOMascotaList> ListaMascotasDuenio = new ArrayList<VOMascotaList>();
		Consultas cons = new Consultas();
		String query = cons.ListarMascotasDuenio();
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
		ResultSet res =pstmt.executeQuery();
			
		while (res.next()) {
			int numero = res.getInt(1);
			String apodo = res.getString(2);
			String raza = res.getString(3);			
			VOMascotaList mascota = new VOMascotaList(numero, apodo, raza);
			ListaMascotasDuenio.add(mascota);
		}
		res.close();
		pstmt.close();
		
		return ListaMascotasDuenio;
		
	}
	
	public static VOMascota obtenerMascota (Connection con, int cedula, int numero) throws SQLException {
		Consultas cons = new Consultas();
		
		String query = cons.ObtenerMascota();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
		pstmt.setInt(2, numero);
		ResultSet rs = pstmt.executeQuery();
		
		VOMascota mascota = null;
		
		if (rs.next()) { //Devuelve una o ninguna mascota, por eso un if
			String apodo = rs.getString(1);
			String raza = rs.getString(2);
			mascota = new VOMascota(apodo, raza);
		}
		rs.close();
		pstmt.close();
		return mascota;
	}
	
	public static Boolean existeMascota (Connection con, int cedula, int numero) throws SQLException {
		Consultas cons = new Consultas();
		
		String query = cons.ExisteInscripcionDuenio();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
		pstmt.setInt(2, numero);
		ResultSet rs = pstmt.executeQuery();
		
		Boolean existe = false;
		
		if (rs.next()) { //Devuelve una o ninguna mascota, por eso un if
			existe=true;
		}
		rs.close();
		pstmt.close();
		return existe;
	}
	
	public static int contarMascotas (Connection con, int cedula, String raza) throws SQLException {
		Consultas cons = new Consultas();
		
		String query = cons.CantidadMascotasDuenio();
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cedula);
		pstmt.setString(2, raza);
		ResultSet rs = pstmt.executeQuery();
		int cantidad = 0;
		
		if (rs.next()) { //Devuelve una o ninguna mascota, por eso un if
			cantidad = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		return cantidad;
	}
	
	
}
