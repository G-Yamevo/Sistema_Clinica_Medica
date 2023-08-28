package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadesDao;
import dao.MedicosDao;
import dao.UsuarioDao;
import entidad.Especialidades;
import entidad.Medicos;
import entidad.TiposDeUsuarios;
import entidad.Usuarios;

public class MedicosDaoImpl implements MedicosDao{
	private static final String buscarMedico = "SELECT * FROM `medicos` WHERE dni=?";
	private static final String insertarMedico = "INSERT INTO `medicos` (dni, cod_Especialidad, dias_Atencion, horarios_Atencion) VALUES (?, ?, ?, ?);";
	private static final String modMedico= "UPDATE medicos SET cod_Especialidad = ?, dias_Atencion = ?, horarios_Atencion = ? WHERE dni =?";
	private static final String readall = "SELECT * FROM `medicos`";
	private static final String buscarxEspecialidad = "SELECT * FROM `medicos` WHERE cod_Especialidad=?";
	private static final String buscarxDia = "SELECT * FROM `medicos` WHERE dias_Atencion=?";
	private static final String buscarxHora = "SELECT * FROM `medicos` WHERE horarios_Atencion=?";
	private static final String busquedaPersonalizada = "SELECT * FROM `medicos`";
	
	
	private Medicos getMedico(ResultSet rs) throws SQLException{
		EspecialidadesDao edi = new EspecialidadesDaoImpl();
		UsuarioDao ud = new UsuarioDaoImpl();
		
		String dni = rs.getString("dni");
		Especialidades es = edi.obtenerEspecialidad(rs.getString("cod_Especialidad"));
		String da = rs.getString("dias_Atencion");
		String ha = rs.getString("horarios_Atencion");
		
		Usuarios u = ud.obtenerUsuario(dni);
		
		TiposDeUsuarios tdu = new TiposDeUsuarios();
		tdu = u.getTiposDeUsuarios();
		String localidad = u.getLocalidad();
		String provincia = u.getProvincia();
		String nacionalidad = u.getNacionalidad();
		String sexo = u.getSexo();
		String nombre = u.getNombre();
		String apellido = u.getApellido();
		String fnac = u.getFechaNacimiento();
		String telefono = u.getTelefono();
		String mail = u.getMail();
		String direc = u.getDireccion();
		String contra = u.getContraseña();
		boolean estado = u.isEstado();
		
		return new Medicos(dni, tdu, localidad, provincia, nacionalidad, sexo, nombre, apellido, fnac, telefono, mail, direc, contra, estado, es, da, ha);
		
	}
	
	@Override
	public Medicos obtenerMedico(String dni) {
		Medicos m = new Medicos();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarMedico);
			st.setString(1, dni);
			rs = st.executeQuery();
			if(rs.next()) {
				m = getMedico(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public boolean insertarMedico(Medicos m) {
		UsuarioDao ud = new UsuarioDaoImpl();
		PreparedStatement st;
		Conexion cn = Conexion.getConexion();
		boolean insertExitoso = false;
		
		try {
			
			if(ud.insertarUsuario(m)) {
				st = cn.getSQLConexion().prepareStatement(insertarMedico);
				st.setString(1, m.getDni());
				st.setString(2, m.getEspecialidad().getCod_especialidad());
				st.setString(3, m.getDiasAtencion());
				st.setString(4, m.getHorariosAtencion());
				if(st.executeUpdate() > 0) {
					cn.getSQLConexion().commit();
					insertExitoso = true;
				}
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
			try {
				cn.getSQLConexion().rollback();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return insertExitoso;
	}

	@Override
	public boolean eliminarMedico(Medicos m) {
		UsuarioDao ud = new UsuarioDaoImpl();
		boolean deleteExitoso = ud.eliminarUsuario(m);
		
		return deleteExitoso;
	}

	@Override
	public boolean modificarMedico(Medicos m) {
		UsuarioDao ud = new UsuarioDaoImpl();
		PreparedStatement st;
		Conexion cn = Conexion.getConexion();
		boolean updateExitoso = false;
		
		try {
			if(ud.modificarUsuario(m)) {
				st = cn.getSQLConexion().prepareStatement(modMedico);
				st.setString(1, m.getEspecialidad().getCod_especialidad());
				st.setString(2, m.getDiasAtencion());
				st.setString(3, m.getHorariosAtencion());
				st.setString(4, m.getDni());
				if(st.executeUpdate() > 0) {
					cn.getSQLConexion().commit();
					updateExitoso = true;
				}
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

	@Override
	public List<Medicos> readAll() {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Medicos> lm = new ArrayList<Medicos>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readall);
			rs = st.executeQuery();
			while(rs.next()) {
				if(getMedico(rs).isEstado() == true) {
					
					lm.add(getMedico(rs));
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lm;
	}

	@Override
	public List<Medicos> buscarxDni(String dni) {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Medicos> lm = new ArrayList<Medicos>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarMedico);
			st.setString(1, dni);
			rs = st.executeQuery();
			while(rs.next()) {
				if(getMedico(rs).isEstado()) {
					
					lm.add(getMedico(rs));
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lm;
	}

	@Override
	public List<Medicos> buscarxEspecialidad(String cod) {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Medicos> lm = new ArrayList<Medicos>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarxEspecialidad);
			st.setString(1, cod);
			rs = st.executeQuery();
			while(rs.next()) {
				if(getMedico(rs).isEstado()) {
					
					lm.add(getMedico(rs));
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lm;
	}

	@Override
	public List<Medicos> buscarxDia(String dia) {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Medicos> lm = new ArrayList<Medicos>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarxDia);

			st.setString(1, dia);
			rs = st.executeQuery();
			while(rs.next()) {
				if(getMedico(rs).isEstado()) {
					
					lm.add(getMedico(rs));
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lm;
	}

	@Override
	public List<Medicos> buscarxHora(String hora) {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Medicos> lm = new ArrayList<Medicos>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarxHora);
			st.setString(1, hora);
			rs = st.executeQuery();
			while(rs.next()) {
				if(getMedico(rs).isEstado()) {
					
					lm.add(getMedico(rs));
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lm;
	}
	
	@Override
	public List<Medicos> busquedaPersonalizada(String consulta){
		
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Medicos> lm = new ArrayList<Medicos>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(busquedaPersonalizada + consulta);
			//st.setString(1, hora);
			rs = st.executeQuery();
			while(rs.next()) {
				if(getMedico(rs).isEstado()) {
					
					lm.add(getMedico(rs));
				}
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return lm;
	}
	
}
