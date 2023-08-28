package excepciones;

public class FechaMayorExcepcion extends RuntimeException{

public FechaMayorExcepcion() {
	
}
	
@Override 
public String getMessage() {
	
	return "Fecha de Nacimiento Invalida";
}
	
}
