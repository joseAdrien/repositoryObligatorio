package persistencia.consultas;

public class Consultas {
	
	public String ExisteDuenio (){
		String query = "select * from certamenmascotas.duenios where cedula = ?";
		return query;
	}
	/* crea el texto de la consulta que verifica la existencia del dueño */
	
	public String CrearDuenio (){
		String query = "insert into certamenmascotas.duenios (cedula, nombre, apellido) values (?, ?, ?)";
		return query;
	}
	/* crea el texto de la consulta que inserta el dueño */
	
	public String UltimaMascotaDuenio (){
		String query = "select max(numInscripcion) from certamenmascotas.mascotas where cedDuenio = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve el numero de inscripcion mas alta dado el dueño*/
	
	public String InsertMascota (){
		String query = "insert into certamenmascotas.mascotas (numInscripcion, apodo, raza, cedDuenio) values (?, ?, ?, ?)";
		return query;
	}
	/* crea el texto de la consulta que inserta una nueva mascota*/
	
	public String BorrarMascotasDuenio (){
		String query = "delete from certamenmascotas.mascotas where cedduenio = ?";
		return query;
	}
	/* crea el texto de la consulta que elimina mascotas de un dueño dado*/
	
	public String BorrarDuenio (){
		String query = "delete from certamenmascotas.duenios where cedula = ?";
		return query;
	}
	/* crea el texto de la consulta que elimina un dueño dado*/
	
	public String ListarDuenios (){
		String query = "select * from certamenmascotas.duenios order by cedula ASC";
		return query;
	}
	/* crea el texto de la consulta que devuelve todos los dueños */
	
	public String ListarMascotasDuenio (){
		String query = "select * from certamenmascotas.mascotas where cedduenio = ? order by numInscripcion ASC";
		return query;
	}
	/* crea el texto de la consulta que devuelve todas las mascotas e un dueño */
	
	public String ExisteInscripcionDuenio (){
		String query = "select * from certamenmascotas.mascotas where cedduenio = ? and numinscripcion = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve todas las mascotas e un dueño */
	/* Esto para cumplir con el procedimiento "obtenerMascota". Tambien se puede hacer mediante la funcion UltimaMascotaDuenio*/
	
	public String ObtenerMascota (){
		String query = "select apodo, raza from certamenmascotas.mascotas where cedduenio = ? and numinscripcion = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve una mascota en especifico*/
	
	public String CantidadMascotasDuenio (){
		String query = "select count(*) from certamenmascotas.mascotas where cedduenio = ? and raza = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve la cantidad de mascotas de un dueño con raza ingresada*/

	public String insback() {
		// TODO Auto-generated method stub
		return null;
	}

	public String largo() {
		// TODO Auto-generated method stub
		return null;
	}

}
