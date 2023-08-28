package negocioImpl;

import java.util.List;

import dao.TiposDeUsuariosDao;
import daoImpl.TiposDeUsuariosDaoImpl;
import entidad.TiposDeUsuarios;
import negocio.TipoDeUsuarioNegocio;

public class TipoDeUsuarioNegocioImpl implements TipoDeUsuarioNegocio{
	
	TiposDeUsuariosDao tdud = new TiposDeUsuariosDaoImpl();
	
	@Override
	public TiposDeUsuarios obtenerTiposDeUsuariosCod(String cod) {
		return tdud.obtenerTiposDeUsuariosCod(cod);
	}

	@Override
	public TiposDeUsuarios obtenerTiposDeUsuariosDes(String des) {
		return tdud.obtenerTiposDeUsuariosDes(des);
	}

	@Override
	public List<TiposDeUsuarios> readAll() {
		return tdud.readAll();
	}

}
