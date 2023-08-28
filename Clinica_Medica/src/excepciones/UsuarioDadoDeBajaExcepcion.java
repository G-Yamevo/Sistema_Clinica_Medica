package excepciones;

public class UsuarioDadoDeBajaExcepcion extends RuntimeException{

    @Override
    public String getMessage() {
        return "El Usuario Se Encuentra Dado de Baja";
    }

}
