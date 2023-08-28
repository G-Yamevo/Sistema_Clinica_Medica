package dao;

import java.util.List;

import entidad.Especialidades;

public interface EspecialidadesDao {
	public Especialidades obtenerEspecialidad(String cod);
	public List<Especialidades> readAll();
}
