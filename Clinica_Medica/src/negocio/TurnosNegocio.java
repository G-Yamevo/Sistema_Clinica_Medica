package negocio;

import java.util.List;

import entidad.Turnos;

public interface TurnosNegocio {
	public boolean insertarTurno(Turnos t);
	public Turnos obtenerTurnos(String cod);
	public List<Turnos> obtenerTurnosxMed(String dni);
	public List<Turnos> readAll();
	public List<Turnos> readAllOcupados();
	public List<Turnos> readAllLibres();
	public boolean modificarEstado(Turnos t);
}
