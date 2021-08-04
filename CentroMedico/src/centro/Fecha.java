package centro;


import java.util.Calendar;

public class Fecha {

    private int dia;//el IRREP es siempre true ya que cualquier se pueden crear fechas con cualquier dia,mes y a�o
    private int mes;
    private int a�o;

    //Constructor por defecto
    public Fecha() {
    }

    //Constructor con par�metros
    public Fecha(int dia, int mes, int a�o)  {
        this.dia = dia;
        this.mes = mes;
        this.a�o = a�o;
    }

    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }
    public int getA�o() {
        return a�o;
    }

    //M�todo para comprobar si la fecha es correcta
    public boolean fechaCorrecta() {
        boolean diaCorrecto, mesCorrecto, a�oCorrecto;
        a�oCorrecto = a�o > 0;
        mesCorrecto = mes >= 1 && mes <= 12;
        switch (mes) {
            case 2:
                if (esBisiesto()) {
                    diaCorrecto = dia >= 1 && dia <= 29;
                } else {
                    diaCorrecto = dia >= 1 && dia <= 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                diaCorrecto = dia >= 1 && dia <= 30;
                break;
            default:
                diaCorrecto = dia >= 1 && dia <= 31;
        }
        return diaCorrecto && mesCorrecto && a�oCorrecto;
    }

    //M�todo privado para comprobar si el a�o es bisiesto
    //Este m�todo lo utiliza el m�todo fechaCorrecta
    private boolean esBisiesto() {
        return (a�o % 4 == 0 && a�o % 100 != 0 || a�o % 400 == 0);
    }

    //M�todo que modifica la fecha cambi�ndola por la del d�a siguiente                                           
    public void diaSiguiente() {
        dia++;
        if (!fechaCorrecta()) {
            dia = 1;
            mes++;
            if (!fechaCorrecta()) {
                mes = 1;
                a�o++;
            }

        }
    }
    public boolean esMenor(Fecha fecha) {
    	if(this.a�o<=fecha.getA�o()) {
    		if(this.mes<=fecha.getMes()) {
    			if(this.dia<fecha.getDia()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public int diferenciaDias(Fecha estaFecha) {
    	//mas chica por parametro
    	int dia= estaFecha.dia;
    	int mes= estaFecha.mes;
    	int a�o= estaFecha.a�o;
    	Calendar masChica= Calendar.getInstance();
    	masChica.set(a�o,mes-1,dia);
    	masChica.set(Calendar.HOUR,0);
    	masChica.set(Calendar.MINUTE,0);
    	masChica.set(Calendar.SECOND,0);
    	
    	Calendar masGrande= Calendar.getInstance();
    	masGrande.set(this.a�o,this.mes-1,this.dia);
    	masGrande.set(Calendar.HOUR,0);
    	masGrande.set(Calendar.MINUTE,0);
    	masGrande.set(Calendar.SECOND,0);
    
    	long finMS=masGrande.getTimeInMillis();
    	long inicioMS=masChica.getTimeInMillis();
    	
    	int dias= (int)((Math.abs(finMS-inicioMS)/(1000*60*60*24)));
    	
    	return dias;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dia < 10) {
            sb.append("0");
        }
        sb.append(dia);
        sb.append("-");
        if (mes < 10) {
            sb.append("0");
        }
        sb.append(mes);
        sb.append("-");
        sb.append(a�o);
        return sb.toString();
    }

	public static Fecha hoy()  {
		int dia=Calendar.DATE;
		int mes=Calendar.MONTH;
		int a�o=Calendar.YEAR;
		
		return new Fecha(dia, mes, a�o);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�o;
		result = prime * result + dia;
		result = prime * result + mes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fecha other = (Fecha) obj;
		if (a�o != other.a�o)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}
	
} 