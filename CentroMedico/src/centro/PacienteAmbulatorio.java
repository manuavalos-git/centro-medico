package centro;

import java.util.Iterator;
import java.util.LinkedList;

public class PacienteAmbulatorio extends Paciente {
	private LinkedList <Tratamiento> tratamientos;
	
	public PacienteAmbulatorio(String nombre, Fecha nacimiento, Integer hc) {
		super(nombre, nacimiento, hc);
		tratamientos= new LinkedList<Tratamiento>();
		// TODO Auto-generated constructor stub
	}
	public void agregarTratamiento(String nombre, Integer hC, Integer matricula,double costo) {
		Tratamiento t=new Tratamiento(nombre, hC, matricula, costo);
		tratamientos.addLast(t);
		this.setSaldo(this.getSaldo()+t.getCosto());
	}
	@Override
	public double pagarSaldo() {
		if (this.getSaldo() == 0) {
			return 0;
		} else {
			Iterator<Tratamiento> it1 = tratamientos.iterator();
					
				while (it1.hasNext()) {
				it1.next().setPagada(true);
				}

		}
		this.setSaldo(0);
		return this.getSaldo();
	}
}
