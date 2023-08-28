package dao;

import java.util.List;

import entidad.Estados;

public interface EstadosDao {
	public Estados obtenerEstado(String cod);
	public List<Estados> readAll();
}
