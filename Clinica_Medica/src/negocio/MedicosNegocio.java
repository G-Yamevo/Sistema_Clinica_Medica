package negocio;

import java.util.List;

import entidad.Medicos;
import entidad.Usuarios;

public interface MedicosNegocio {

	public Medicos obtenerMedico(String dni);
	public boolean insertarMedico(Medicos m);
	public boolean eliminarMedico(Medicos m);
	public boolean modificarMedico(Medicos m);
	public List<Medicos> readAll();
	public List<Medicos> buscarxDni(String dni);
	public List<Medicos> buscarxEspecialidad(String cod);
	public List<Medicos> buscarxDia(String dia);
	public List<Medicos> buscarxHora(String hora);
	public List<Medicos> busquedaPersonalizada(String consulta);
}
