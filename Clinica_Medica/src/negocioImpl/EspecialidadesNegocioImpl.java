package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadesDao;
import daoImpl.EspecialidadesDaoImpl;
import entidad.Especialidades;
import negocio.EspecialidadesNegocio;

public class EspecialidadesNegocioImpl implements EspecialidadesNegocio{
	
	EspecialidadesDao ed = new EspecialidadesDaoImpl();

	@Override
	public Especialidades obtenerEspecialidad(String cod) {
		return ed.obtenerEspecialidad(cod);
	}

	@Override
	public ArrayList<Especialidades> readAll() {
		return (ArrayList<Especialidades>) ed.readAll();
	}

}
