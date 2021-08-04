package centro;

import java.util.Iterator;
import java.util.LinkedList;

public  class PacientePrivado extends Paciente {
	private LinkedList <Atencion> atencionesCons;// atencionesCons.get(0).getFecha<atencionesCons.get(1).getFecha<atencionesCons.get(2).getFecha
	private LinkedList <Atencion> atencionesGuard;//atencionesGard.get(0).getFecha<atencionesGuard.get(1).getFecha<atencionesGuard.get(2).getFecha  
	
	
	public PacientePrivado(String nombre, Fecha nacimiento, Integer hc) {
		super(nombre, nacimiento, hc);
		atencionesCons= new LinkedList<Atencion>();
		atencionesGuard= new LinkedList<Atencion>();
		// TODO Auto-generated constructor stub
	}
	public LinkedList<Atencion> getAtencionesCons() {
		return atencionesCons;
	}
	public void setAtencionesCons(LinkedList<Atencion> atencionesCons) {
		this.atencionesCons = atencionesCons;
	}
	public boolean agregarAtencionCons(Fecha fecha, int matricula) {
		if(!atencionesCons.isEmpty() && !fecha.esMenor(atencionesCons.getLast().getFecha())) {
			atencionesCons.addLast(new Atencion(this.getHc(),fecha,matricula));
			return true;
		}
		else if(atencionesCons.isEmpty()) {
			atencionesCons.addLast(new Atencion(this.getHc(),fecha,matricula));
			return true;
		}
		return false; 
	}		
	public boolean agregarAtencionGuar(Fecha fecha) {
		if(!atencionesGuard.isEmpty() && !fecha.esMenor(atencionesGuard.getLast().getFecha())) {
			atencionesGuard.addLast(new Atencion(this.getHc(), fecha));
			return true;
		}
		else if(atencionesGuard.isEmpty()) {
			atencionesGuard.addLast(new Atencion(this.getHc(),fecha));
			return true;
		}
		return false;
	}
	@Override
	public double pagarSaldo() {
		if(this.getSaldo()==0) {
			return 0;
		}
		else {
			this.setSaldo(0);
			Iterator<Atencion> it1 = atencionesCons.iterator();
			while(it1.hasNext()) {
				it1.next().setPagada(true);;
			}
		}
		return this.getSaldo();
	}	
	
}
