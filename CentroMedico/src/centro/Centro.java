package centro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Centro {
	private String nombre;//!equals("")
	private String cuit;//!equals("") 
	private double valorInt;//>=0
	private int contEsp=0; //contador para el codigo de las especialidades.
	private int contHabit=0;
	private HashMap <String,Especialidad> espe;//espe.get(nombre1)!=espe.get(nombre2)
	private HashMap <Integer,PacientePrivado> pPrivados;//
	private HashMap <Integer,PacienteAmbulatorio> pAmbulatorios;
	private HashMap <Integer,PacienteObraSocial> pObraS;
	private HashMap <Integer,Medico> medicos;
	private ArrayList <ObraSocial> obras;
	
	
	
	public Centro(String nombre, String cuit, double valorInt){
		this.nombre=nombre;
		this.cuit=cuit;
		this.valorInt=valorInt;
		pPrivados=new HashMap <Integer,PacientePrivado>() ;
		espe=new HashMap <String,Especialidad>();
		pAmbulatorios=new HashMap <Integer,PacienteAmbulatorio>();
		pObraS=new HashMap <Integer,PacienteObraSocial>();
		medicos=new HashMap <Integer,Medico>();
		obras=new ArrayList <ObraSocial>();
		
	}

	public double getValorInt() {
		return valorInt;
	}

	public boolean agregarEspecialidad(String nombre, double valor) {
		
		if(!espe.containsKey(nombre)) {
			contEsp++;
			espe.put(nombre, new Especialidad(nombre,valor,contEsp));
			return true;
		}
		else return false;
		
	}

	public boolean agregarMedico(String nombre, int matricula, String nomEspecialidad, double valorTratamiento) {
		
		if(!medicos.containsKey(matricula) && espe.containsKey(nomEspecialidad)){
			medicos.put(matricula, new Medico(matricula,nombre,nomEspecialidad,valorTratamiento));
			return  true;
		}
		else return false;
	}

	public boolean agregarPacientePrivado(String nombre, Integer hc, Fecha nac){
		if(!pPrivados.containsKey(hc)) {
			pPrivados.put(hc, new PacientePrivado(nombre, nac, hc));
			
			return true;
		}
		else return false;
//		Pacientes Privados, que son los pacientes que ingresan al Centro y que
//		pueden asistir a consultorios externos o a guardia cuando lo deseen.
		
	}
//
	public boolean agregarPacienteAmbulatorio(String nombre, Integer hc, Fecha nac) {
		if(!pAmbulatorios.containsKey(hc)) {
			pAmbulatorios.put(hc, new PacienteAmbulatorio(nombre, nac, hc));
			return true;
		}
		else return false;
	}

	public boolean agregarPacienteObraSocial(String nombre, int hC, Fecha nac, String osocial, double p) {
		
		boolean esta=false;
		ObraSocial o=new ObraSocial(osocial,p);
		if(!pObraS.containsKey(hC)) {
			pObraS.put(hC, new PacienteObraSocial(nombre, nac, hC,o, this.valorInt));
			for(ObraSocial ob: obras ) {
				esta=esta || ob.equals(o); 
			}
			if(!esta) {
				obras.add(o);
			}
			return true;
		}
		else return false;
	}
	public boolean agregarAtencion(int hC, Fecha fecha, int matricula) { 
	//en el caso de atención en consultorio.
		String espeNombre;
		double costo = 0;
		if(pPrivados.containsKey(hC)&& pPrivados.get(hC).agregarAtencionCons(fecha, matricula)&& medicos.containsKey(matricula)) {
			espeNombre=medicos.get(matricula).getNombrEsp();
			costo=espe.get(espeNombre).getValor();
			pPrivados.get(hC).sumarCosto(costo);
			return true;
		}
		else return false;
	}	
	public boolean agregarAtencion(int hC, Fecha fecha) {
//	//en el caso de atención por
//	//guardia.
		double costoGuard= 0;
		if(pPrivados.containsKey(hC)&& pPrivados.get(hC).agregarAtencionGuar(fecha)) {
			pPrivados.get(hC).sumarCosto(costoGuard);
			return true;
		}
		else return false;
	}
	public boolean agregarInternacion(int hC, String area, Fecha fingreso) {
//	//ingresa el
//	//paciente a internación.
		//double porcentaje=0;
		if(area.equals("Cardiologia")||area.equals("Pediatria")||area.equals("General")){
			contHabit++;
			if(pObraS.containsKey(hC) && pObraS.get(hC).agregarInternacion(area, fingreso, contHabit)){
				return true;
			}
			else return false;
		}
		else return false;
	}
	public boolean altaInternacion(int hC, Fecha fechaAlta) {
// No puede ser anterior a la fecha de internación.
		if(pObraS.containsKey(hC) && pObraS.get(hC).altaInternacion(fechaAlta)) {
			return true;
		}
		else return false;
	}	
	public boolean agregarTratamiento(int hC, int matricula, String tratamiento) {
		if(pAmbulatorios.containsKey(hC)&& medicos.containsKey(matricula)) {
			double costo=medicos.get(matricula).getValorTratamiento();
			pAmbulatorios.get(hC).agregarTratamiento(tratamiento, hC, matricula,costo);
			return true;
		}
		return false;
	}
	double getSaldo(int hC){
		if(pPrivados.containsKey(hC)) {
			return pPrivados.get(hC).getSaldo();
		}
		else if(pAmbulatorios.containsKey(hC)){
			return pAmbulatorios.get(hC).getSaldo();
		}
		else if(pObraS.containsKey(hC)){
			return pObraS.get(hC).getSaldo();
		}
		return 0;
	}
	
	void pagarSaldo(int hC)   {
//		cuando pagamos poner en true las atenciones hasta el momento,
		if(pPrivados.containsKey(hC)) {
			pPrivados.get(hC).pagarSaldo();
		}
		else if(pAmbulatorios.containsKey(hC)){
			pAmbulatorios.get(hC).pagarSaldo();
		}
		else if(pObraS.containsKey(hC)){
			pObraS.get(hC).pagarSaldo();
		}
	}
	public Map<Fecha, String> atencionesEnConsultorio(int hC){
		HashMap<Fecha,String> atenCons = new HashMap<Fecha,String> ();
		if(pPrivados.containsKey(hC)) {
			for(Atencion a: pPrivados.get(hC).getAtencionesCons()) {
				atenCons.put(a.getFecha(), medicos.get(a.getMatricula()).getNombrEsp());			
			}
		}
		return atenCons;
	}
	
	public List<Integer> listaInternacion() {
		LinkedList<Integer> ListInter= new LinkedList<Integer>();
		for (Entry<Integer, PacienteObraSocial> p: pObraS.entrySet()) {
			if(!p.getValue().getInternaciones().isEmpty() && p.getValue().getInternaciones().getLast().getfAlta().equals(new Fecha(1,1,1))) { 
				ListInter.addLast(p.getKey());
			}
		}
		return ListInter;
	}

	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(cuit);
		sb.append(" ");
		sb.append("/");
		sb.append(nombre);
		sb.append("/");
		sb.append(" ");
		sb.append("Valor de internacion: ");
		sb.append(valorInt);
		return sb.toString();
	}
	
}	
