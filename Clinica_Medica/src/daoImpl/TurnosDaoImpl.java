package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EstadosDao;
import dao.TurnosDao;
import entidad.Estados;
import entidad.Turnos;
import entidad.Medicos;
import entidad.Usuarios;
import entidad.Especialidades;
import daoImpl.MedicosDaoImpl;
import daoImpl.UsuarioDaoImpl;
import daoImpl.EspecialidadesDaoImpl;;

public class TurnosDaoImpl implements TurnosDao{
	private static final String insertarTurno = "INSERT INTO `turnos` (dni_Medico, dni_Paciente, cod_Especialidad, cod_Estado, dia, horario) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String buscarTurno = "SELECT * FROM turnos WHERE cod_Turno=?";
	private static final String buscarTurnoxMed = "SELECT * FROM turnos WHERE dni_Medico=?";
	private static final String readall = "SELECT * FROM turnos";
	private static final String readallocupados = "SELECT * FROM turnos WHERE cod_Estado=2";
	private static final String readalllibres = "SELECT * FROM turnos WHERE cod_Estado=1";
	private static final String updateEstado = "UPDATE turnos SET cod_estado =? WHERE cod_Turno=?";
	
	@Override
	public Turnos obtenerTurnos(String cod) {
		Turnos t = new Turnos();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarTurno);
			st.setString(1, cod);
			rs = st.executeQuery();
			if(rs.next()) {
				t = getTurnos(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public List<Turnos> readAll() {
		PreparedStatement st;
		ArrayList<Turnos> lt = new ArrayList<Turnos>();
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readall);
			rs = st.executeQuery();
			while(rs.next()) {
					lt.add(getTurnos(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}

	@Override
	public List<Turnos> obtenerTurnosxMed(String dni) {
		PreparedStatement st;
		ArrayList<Turnos> lt = new ArrayList<Turnos>();
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarTurnoxMed);
			st.setString(1, dni);
			rs = st.executeQuery();
			while(rs.next()) {
					lt.add(getTurnos(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}
	
	public Turnos getTurnos(ResultSet rs) throws SQLException{
		EstadosDao ed = new EstadosDaoImpl();
		String cod = rs.getString("cod_Turno");
		String dni_Med = rs.getString("dni_Medico");
		String dni_Pac = rs.getString("dni_Paciente");
		String cod_Esp = rs.getString("cod_Especialidad");
		String dia = rs.getString("dia");
		String hora = rs.getString("horario");
		
		Estados es = ed.obtenerEstado(rs.getString("cod_Estado"));
		
		return new Turnos(cod, dni_Med, dni_Pac, cod_Esp, es, dia, hora);
	}
	
	
	public String obtenerNombreMed(String dni) {
		MedicosDaoImpl dao= new MedicosDaoImpl();
		Medicos med= new Medicos();
		
		med=dao.obtenerMedico(dni);
		
		String nombre=med.getNombre() + " " + med.getApellido();
		
		return nombre;
	}

	
	public String obtenerNombrePac(String dni) {
		UsuarioDaoImpl dao= new UsuarioDaoImpl();
		Usuarios pac= new Usuarios();
		
		pac=dao.obtenerUsuario(dni);
		
		String nombre=pac.getNombre() + " " + pac.getApellido();
		
		return nombre;
	}
	
	
	public String obtenerMailMed(String dni) {
		MedicosDaoImpl dao= new MedicosDaoImpl();
		Medicos med= new Medicos();
		
		med=dao.obtenerMedico(dni);
		
		String mail=med.getMail();
		
		return mail;
	}
	
	
	public String obtenerDescEsp(String cod) {
		EspecialidadesDaoImpl dao= new EspecialidadesDaoImpl();
		Especialidades esp= new Especialidades();
		
		esp=dao.obtenerEspecialidad(cod);
		String desc=esp.getDescripcion();
		
		return desc;
	}
	
	public List<Turnos> readAllOcupados() {
		PreparedStatement st;
		ArrayList<Turnos> lt = new ArrayList<Turnos>();
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readallocupados);
			rs = st.executeQuery();
			while(rs.next()) {
					lt.add(getTurnos(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}
	
	public List<Turnos> readAllLibres() {
		PreparedStatement st;
		ArrayList<Turnos> lt = new ArrayList<Turnos>();
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readalllibres);
			rs = st.executeQuery();
			while(rs.next()) {
					lt.add(getTurnos(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}

	@Override
	public boolean insertarTurno(Turnos t) {
		PreparedStatement st;
		Conexion conexion = Conexion.getConexion();
		boolean isInsertExitoso = false;
		try
		{
			st = conexion.getSQLConexion().prepareStatement(insertarTurno);
			st.setString(1, t.getDni_medico());
			st.setString(2, t.getDni_paciente());
			st.setString(3, t.getCod_especialidad());
			st.setString(4, t.getEstado().getCod_estado());
			st.setString(5, t.getDia());
			st.setString(6, t.getHora());
			
			if(st.executeUpdate() > 0) {
				conexion.getSQLConexion().commit();
				isInsertExitoso = true;
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.getSQLConexion().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	@Override
	public boolean modificarEstado(Turnos t) {
		PreparedStatement st;
		Conexion cn = Conexion.getConexion();
		boolean updateExitoso = false;
		try {
			st = cn.getSQLConexion().prepareStatement(updateEstado);
			st.setString(1, t.getEstado().getCod_estado());
			st.setString(2, t.getCod_turno());
			
			if(st.executeUpdate() > 0) {
				cn.getSQLConexion().commit();
				updateExitoso = true;
			}	
		} catch(SQLException e1) {
			e1.printStackTrace();
			try {
				cn.getSQLConexion().rollback();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return updateExitoso;
	}
}
