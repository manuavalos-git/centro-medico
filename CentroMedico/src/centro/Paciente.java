package centro;

public abstract class Paciente{
	private String nombre;//!equals("")
	private Fecha nacimiento;
	private Integer hc;//>=0
	private double saldo;//>0 si tiene deuda && ==0 si salda su deuda
	
	
	public Paciente(String nombre,Fecha nacimiento,Integer hc)  {
	
		this.nombre=nombre;
		this.nacimiento=nacimiento;
		this.hc=hc;
	
	}
	public abstract double pagarSaldo();
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Integer getHc() {
		return hc;
	}
	public Fecha getNacimiento() {
		return nacimiento;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNacimiento(Fecha nacimiento) {
		this.nacimiento = nacimiento;
	}
	
	public void sumarCosto(double costo) {
		this.saldo+=costo;
	}
}
