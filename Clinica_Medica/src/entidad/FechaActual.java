package entidad;

import java.time.*;
import excepciones.FechaMayorExcepcion;

import excepciones.HorarioAtencionExcepcion;

public class FechaActual {
private LocalDate fecha;

public FechaActual() {
	this.fecha=LocalDate.now();
}

public static boolean verificarFecha(LocalDate f) throws FechaMayorExcepcion {
	
	if(LocalDate.now().compareTo(f)>0) {
		//fecha nacimiento es menor a la actual
		FechaMayorExcepcion Excepcion= new FechaMayorExcepcion();
		throw Excepcion;
	}
	
	return false;
}

}
