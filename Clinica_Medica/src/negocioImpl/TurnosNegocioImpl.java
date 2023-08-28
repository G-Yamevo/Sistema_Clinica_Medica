package negocioImpl;

import java.util.List;

import dao.TurnosDao;
import daoImpl.TurnosDaoImpl;
import entidad.Medicos;
import entidad.Turnos;
import negocio.TurnosNegocio;

public class TurnosNegocioImpl implements TurnosNegocio{
	
	TurnosDao td = new TurnosDaoImpl();
	
	@Override
	public boolean insertarTurno(Turnos t) {
		return td.insertarTurno(t);
	}
	
	@Override
	public Turnos obtenerTurnos(String cod) {
		return td.obtenerTurnos(cod);
	}

	@Override
	public List<Turnos> obtenerTurnosxMed(String dni) {
		return td.obtenerTurnosxMed(dni);
	}

	@Override
	public List<Turnos> readAll() {
		return td.readAll();
	}
	
	@Override
	public List<Turnos> readAllOcupados() {
		return td.readAllOcupados();
	}
	
	@Override
	public List<Turnos> readAllLibres() {
		return td.readAllLibres();
	}

	@Override
	public boolean modificarEstado(Turnos t) {
		return td.modificarEstado(t);
	}

}
