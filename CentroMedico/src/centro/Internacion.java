package centro;

public class Internacion {
	private int hc;//>=0
	private String area;//area.equals("Cardiologia")||area.equals("Pediatria")||area.equals("General")
	private Fecha fIngreso;//siempre > a la ultima fecha de alta || > (1,1,1)
	private Fecha fAlta;//  > a la ultima fecha de ingreso || equals(1,1,1)
	private int nHabitacion;//nHabitacion siempre es diferente
	private double costoIter;// == centro.getValorInt()
	private boolean pagada;//solo si se salda la cuenta debe ser true
	
	public Internacion(int hc,String area,Fecha fingreso,int nHabitacion) {
		this.hc=hc;
		this.area=area;
		this.fIngreso=fingreso;
		this.nHabitacion=nHabitacion;
		this.fAlta= new Fecha(1,1,1);
		this.pagada=false;
	}
	public double getCostoIter() {
		return costoIter;
	}
	public void setCostoIter(double costoIter) {
		this.costoIter = costoIter;
	}
	public Fecha getFingreso() {
		return fIngreso;
	}
	public void setfAlta(Fecha fAlta) {
		//No puede ser anterior a la fecha de internación.
			this.fAlta = fAlta;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
	public Fecha getfAlta() {
		return fAlta;
	}
	
	
}
