package centro;

public class Especialidad {
	private String nombre;
	private double valor;
	private int codigo;

	public Especialidad(String nombre, double valor,int codigo) {
		this.nombre = nombre;
		this.valor=valor;
		this.codigo=codigo;
	}

	public double getValor() {
		return valor;
	}
	
}
