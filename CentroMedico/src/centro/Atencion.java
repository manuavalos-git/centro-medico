package centro;
//++++//
public class Atencion {
	
	private int hc;
	private Fecha fecha;
	private int matricula;
	private boolean pagada;
	
	public Atencion(int hc, Fecha fecha){
		this.hc=hc;
		this.fecha=fecha;
		this.pagada=false;
	}
	public Atencion(int hc, Fecha fecha,int matricula){
		this.hc=hc;
		this.fecha=fecha;
		this.matricula=matricula;
		this.pagada=false;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
	public Fecha getFecha() {
		return fecha;
	}
	public int getMatricula() {
		return matricula;
	}
	public int getHc() {
		return hc;
	}

	
}
