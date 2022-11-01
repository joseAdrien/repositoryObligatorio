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

	public Mascota() {
		// TODO Auto-generated constructor stub
	}

	public long getNumlnsc() {
		return numlnsc;
	}

	public void setNumlnsc(int numlnsc) {
		this.numlnsc = numlnsc;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}
}
	

