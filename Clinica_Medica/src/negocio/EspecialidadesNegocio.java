package negocio;

import java.util.List;

import entidad.Especialidades;

public interface EspecialidadesNegocio {
	public Especialidades obtenerEspecialidad(String cod);
	public List<Especialidades> readAll();
}
