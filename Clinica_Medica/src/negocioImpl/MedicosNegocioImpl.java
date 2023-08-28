package negocioImpl;

import java.util.List;

import dao.MedicosDao;
import daoImpl.MedicosDaoImpl;
import entidad.Medicos;
import entidad.Usuarios;
import negocio.MedicosNegocio;

public class MedicosNegocioImpl implements MedicosNegocio{
	private MedicosDao md = new MedicosDaoImpl();
	@Override
	public Medicos obtenerMedico(String dni) {
		
		return md.obtenerMedico(dni);
	}
	@Override
	public boolean insertarMedico(Medicos m) {
		return md.insertarMedico(m);
	}
	@Override
	public boolean eliminarMedico(Medicos m) {
		return md.eliminarMedico(m);
	}
	@Override
	public boolean modificarMedico(Medicos m) {
		return md.modificarMedico(m);
	}
	@Override
	public List<Medicos> readAll() {
		return md.readAll();
	}
	@Override
	public List<Medicos> buscarxDni(String dni) {
		return md.buscarxDni(dni);
	}
	@Override
	public List<Medicos> buscarxEspecialidad(String cod) {
		return md.buscarxEspecialidad(cod);
	}
	@Override
	public List<Medicos> buscarxDia(String dia) {
		return md.buscarxDia(dia);
	}
	@Override
	public List<Medicos> buscarxHora(String hora) {
		return md.buscarxHora(hora);
	}
	@Override
	public List<Medicos> busquedaPersonalizada(String consulta){
		return md.busquedaPersonalizada(consulta);
	};
	
}
