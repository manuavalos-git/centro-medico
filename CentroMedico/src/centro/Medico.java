package centro;

public class Medico{
	private int matricula;
	private String nombre;
	private String nombrEsp;
	private double valorTratamiento;
	
	public Medico(int matricula, String nombre,String nombrEsp,double valorTratamiento) {
	
		this.matricula = matricula;
		this.nombre = nombre;
		this.nombrEsp = nombrEsp;
		this.valorTratamiento = valorTratamiento;
		
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public String getNombrEsp() {
		return nombrEsp;
	}
	public void setNombrEsp(String nombrEsp) {
		this.nombrEsp = nombrEsp;
	}
	public double getValorTratamiento() {
		return valorTratamiento;
	}
	public void setValorTratamiento(Integer valorTratamiento) {
		this.valorTratamiento = valorTratamiento;
	}
	public String getNombre() {
		return nombre;
	}
	
	
}
