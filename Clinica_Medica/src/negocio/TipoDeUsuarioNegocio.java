package negocio;

import java.util.List;

import entidad.TiposDeUsuarios;

public interface TipoDeUsuarioNegocio {
	public TiposDeUsuarios obtenerTiposDeUsuariosCod(String cod);
	public TiposDeUsuarios obtenerTiposDeUsuariosDes(String des);
	public List<TiposDeUsuarios> readAll();
}
