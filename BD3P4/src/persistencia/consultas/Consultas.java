package persistencia.consultas;

public class Consultas {
	
	public String existsCedula (){
		String query = "select * from certamenmascotas.duenios where cedula = ?";
		return query;
	}
	/* crea el texto de la consulta que verifica la existencia del dueño */
	
	public String insertDuenio (){
		String query = "insert into certamenmascotas.duenios (cedula, nombre, apellido) values (?, ?, ?)";
		return query;
	}
	/* crea el texto de la consulta que inserta el dueño */
	
	public String UltimaMascotaDuenio (){
		String query = "select max(numInscripcion) from certamenmascotas.mascotas where cedDuenio = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve el numero de inscripcion mas alta dado el dueño*/
	
	
	/* crea el texto de la consulta que inserta una nueva mascota*/
	
	public String deleteMascotas (){
		String query = "delete from certamenmascotas.mascotas where cedduenio = ?";
		return query;
	}
	/* crea el texto de la consulta que elimina mascotas de un dueño dado*/
	
	public String deleteDuenio (){
		String query = "delete from certamenmascotas.duenios where cedula = ?";
		return query;
	}
	/* crea el texto de la consulta que elimina un dueño dado*/
	
	public String listarDuenios (){
		String query = "select * from certamenmascotas.duenios order by cedula ASC";
		return query;
	}
	/* crea el texto de la consulta que devuelve todos los dueños */
	
	public String listarMascotas (){
		String query = "select * from certamenmascotas.mascotas where cedduenio = ? order by numInscripcion ASC";
		return query;
	}
	/* crea el texto de la consulta que devuelve todas las mascotas e un dueño */
	
	
	/* crea el texto de la consulta que devuelve todas las mascotas e un dueño */
	/* Esto para cumplir con el procedimiento "obtenerMascota". Tambien se puede hacer mediante la funcion UltimaMascotaDuenio*/
	
	public String ObtenerMascota (){
		String query = "select apodo, raza from certamenmascotas.mascotas where cedduenio = ? and numinscripcion = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve una mascota en especifico*/
	
	public String contarMascotas (){
		String query = "select count(*) from certamenmascotas.mascotas where cedduenio = ? and raza = ?";
		return query;
	}
	/* crea el texto de la consulta que devuelve la cantidad de mascotas de un dueño con raza ingresada*/

	public String insback() {
		String query = "insert into certamenmascotas.mascotas (numInscripcion, apodo, raza, cedDuenio) values (?, ?, ?, ?)";
		return query;
	}

	public String largo() {
		String query = "select count(*) from certamenmascotas.mascotas where cedduenio = ? ";
		return query;
	}

	public String kmascota() {
		String query = "select * from certamenmascotas.mascotas where cedduenio = ? and numinscripcion = ?";
		return query;
	}



	public String findDuenio() {
		String query = "select * from certamenmascotas.duenios where cedula = ?";
		return query;
	}


}
