package excepciones;

public class HorarioAtencionExcepcion extends RuntimeException{

public HorarioAtencionExcepcion() {
	
}
	
@Override 
public String getMessage() {
	
	return "Horario Invalido";
}
	
}
