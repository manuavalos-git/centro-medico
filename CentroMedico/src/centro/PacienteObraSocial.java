package centro;

import java.util.Iterator;
import java.util.LinkedList;

public class PacienteObraSocial extends Paciente {
	private ObraSocial obrasocial;
	private LinkedList <Internacion> internaciones;
	double valorInter;

	
	public PacienteObraSocial(String nombre, Fecha nacimiento, Integer hc, ObraSocial obrasocial,double valorInter){
		super(nombre, nacimiento, hc);
		this.obrasocial = obrasocial;
		this.valorInter=valorInter;
		internaciones=new LinkedList<Internacion>();
	}
	
	public ObraSocial getObrasocial() {
		return obrasocial;
	}

	public void setObrasocial(ObraSocial obrasocial) {
		this.obrasocial = obrasocial;
	}

	@Override
	public double pagarSaldo() {
		if (this.getSaldo() == 0) {
			return this.getSaldo();
		} else {
			this.setSaldo(0);
			Iterator<Internacion> it1 = internaciones.iterator();
			
			while (it1.hasNext()) {
				it1.next().setPagada(true);
				
			}
			if(this.internaciones.getLast().getfAlta().equals(new Fecha(1,1,1))) {
				internaciones.getLast().setPagada(false);
			}
		}
		return this.getSaldo();
	}
	public LinkedList<Internacion> getInternaciones() {
		return internaciones;
	}

	public boolean agregarInternacion(String area,Fecha fingreso,int nHabitacion) {
		//la fecha alta tiene que ser menor que fecha ingreso && fecha alta !equals 1,1,1
		if(!internaciones.isEmpty()) {
			if(!fingreso.esMenor(internaciones.getLast().getfAlta()) && !internaciones.getLast().getfAlta().equals(new Fecha(1,1,1))) {
				internaciones.addLast(new Internacion(this.getHc(), area, fingreso, nHabitacion));
				return true;
			}
			else{
				return false;
			}
		}
		else {
			internaciones.addLast(new Internacion(this.getHc(), area, fingreso, nHabitacion));
			return true;
		}
	}

	public boolean altaInternacion(Fecha fechaAlta){
		if(!internaciones.isEmpty()&& internaciones.getLast().getfAlta().equals(new Fecha(1,1,1))) {
			
			if(!fechaAlta.esMenor(internaciones.getLast().getfAlta())) {
				
				internaciones.getLast().setfAlta(fechaAlta);
				int diasInt;
				double total=0;
				double porcentaje=this.obrasocial.getCosto();
				for(Internacion in: internaciones) {
					if(!in.isPagada()) {
						diasInt=0;
						diasInt=in.getfAlta().diferenciaDias(in.getFingreso());
						in.setCostoIter(porcentaje*diasInt*this.valorInter);
						total+=in.getCostoIter();
					}
				}
				this.setSaldo(total);
				return true;
			}

		}
		return false;
	}

}
