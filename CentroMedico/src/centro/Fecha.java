package centro;


import java.util.Calendar;

public class Fecha {

    private int dia;//el IRREP es siempre true ya que cualquier se pueden crear fechas con cualquier dia,mes y año
    private int mes;
    private int año;

    //Constructor por defecto
    public Fecha() {
    }

    //Constructor con parámetros
    public Fecha(int dia, int mes, int año)  {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }
    public int getAño() {
        return año;
    }

    //Método para comprobar si la fecha es correcta
    public boolean fechaCorrecta() {
        boolean diaCorrecto, mesCorrecto, añoCorrecto;
        añoCorrecto = año > 0;
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
        return diaCorrecto && mesCorrecto && añoCorrecto;
    }

    //Método privado para comprobar si el año es bisiesto
    //Este método lo utiliza el método fechaCorrecta
    private boolean esBisiesto() {
        return (año % 4 == 0 && año % 100 != 0 || año % 400 == 0);
    }

    //Método que modifica la fecha cambiándola por la del día siguiente                                           
    public void diaSiguiente() {
        dia++;
        if (!fechaCorrecta()) {
            dia = 1;
            mes++;
            if (!fechaCorrecta()) {
                mes = 1;
                año++;
            }

        }
    }
    public boolean esMenor(Fecha fecha) {
    	if(this.año<=fecha.getAño()) {
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
    	int año= estaFecha.año;
    	Calendar masChica= Calendar.getInstance();
    	masChica.set(año,mes-1,dia);
    	masChica.set(Calendar.HOUR,0);
    	masChica.set(Calendar.MINUTE,0);
    	masChica.set(Calendar.SECOND,0);
    	
    	Calendar masGrande= Calendar.getInstance();
    	masGrande.set(this.año,this.mes-1,this.dia);
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
        sb.append(año);
        return sb.toString();
    }

	public static Fecha hoy()  {
		int dia=Calendar.DATE;
		int mes=Calendar.MONTH;
		int año=Calendar.YEAR;
		
		return new Fecha(dia, mes, año);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + año;
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
		if (año != other.año)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}
	
} 