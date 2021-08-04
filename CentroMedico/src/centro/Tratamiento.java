package centro;

public class Tratamiento {
	private String nombre;
	private Integer hc;
	private Integer medicoMtr;
	private double costo;
	private boolean pagada;
	
	public Tratamiento(String nombre, Integer hc, Integer medicoMtr,double costo) {
		this.nombre = nombre;
		this.hc = hc;
		this.medicoMtr = medicoMtr;
		this.costo=costo;
		this.pagada=false;
	}

	public boolean isPagada() {
		return pagada;
	}

	public double getCosto() {
		return costo;
	}

	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
	
	
}
