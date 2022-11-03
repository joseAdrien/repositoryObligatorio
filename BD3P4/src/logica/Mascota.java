package logica;

public class Mascota {


	private int numlnsc;

	private String apodo;

	private String raza;
	

	public Mascota(int numlnsc, String apodo, String raza) {
		super();
		this.numlnsc = numlnsc;
		this.apodo = apodo;
		this.raza = raza;
	}


	public long getNumlnsc() {
		return numlnsc;
	}



	public String getApodo() {
		return apodo;
	}



	public String getRaza() {
		return raza;
	}


}
	

