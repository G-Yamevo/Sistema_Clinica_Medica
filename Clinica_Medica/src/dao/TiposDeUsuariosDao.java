package dao;

import java.util.List;

import entidad.TiposDeUsuarios;

public interface TiposDeUsuariosDao {
	public TiposDeUsuarios obtenerTiposDeUsuariosCod(String cod);
	public TiposDeUsuarios obtenerTiposDeUsuariosDes(String des);
	public List<TiposDeUsuarios> readAll();
}
