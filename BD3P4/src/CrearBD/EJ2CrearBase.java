package CrearBD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EJ2CrearBase {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		try	{			
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
			
			/* Creo un Statement para crear la base de datos en el servidor */
			Statement stmt = con.createStatement();
			String create = "create database certamenmascotas";
			/* Ejecuto la sentencia y cierro el Statement */
			stmt.executeUpdate(create);
			
			System.out.println("Base de datos creada");

			/* Creamos las tablas */
			String createTable = "create table certamenmascotas.Duenios (cedula INT, nombre VARCHAR(45), apellido VARCHAR(45), primary key (cedula))";
			stmt.executeUpdate(createTable);
			
			createTable = "create table certamenmascotas.mascotas (numInscripcion INT, apodo varchar (45), raza varchar (45), cedDuenio INT, primary key (numInscripcion, apodo), CONSTRAINT fk_ceddue FOREIGN KEY (cedDuenio) REFERENCES Duenios (cedula))";
			stmt.executeUpdate(createTable);
			
			System.out.println("Tablas creadas");
			
			/* Insertamos datos */
			String insertTable = "insert into certamenmascotas.Duenios values ('1234567', 'Jimi', 'Hendrix')";
			stmt.executeUpdate(insertTable);

			insertTable = "insert into certamenmascotas.Duenios values ('2345678', 'Janis', 'Joplin')";
			stmt.executeUpdate(insertTable);

			insertTable = "insert into certamenmascotas.Duenios values ('3456789', 'Jim', 'Morrison')";
			stmt.executeUpdate(insertTable);

			insertTable = "insert into certamenmascotas.Duenios values ('4567890', 'Kurt', 'Cobain')";
			stmt.executeUpdate(insertTable);

			insertTable = "insert into certamenmascotas.Duenios values ('5678901', 'Amy', 'Winehouse')";
			stmt.executeUpdate(insertTable);
			
			System.out.println("Datos insertados en CERTAMENMASCOTAS");
			stmt.close();
			con.close();
			
		}catch (SQLException e){
			e.printStackTrace();
		}

	}

}
