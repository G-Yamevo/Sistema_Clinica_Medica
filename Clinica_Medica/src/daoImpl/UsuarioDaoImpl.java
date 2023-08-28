package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TiposDeUsuariosDao;
import dao.UsuarioDao;
import entidad.TiposDeUsuarios;
import entidad.Usuarios;

public class UsuarioDaoImpl implements UsuarioDao{
	private static final String buscarDni = "SELECT * FROM `usuarios` WHERE dni=?";
	private static final String buscarContra = "SELECT contra FROM usuarios WHERE dni=?";
	private static final String insert = "INSERT INTO `usuarios` (`dni`,`cod_Tipo`,`localidad`,`provincia`,`nacionalidad`,`sexo`,`nombre`,`apellido`,`fecha_Nacimiento`,`telefono`,`mail`,`direccion`,`contra`,`estado`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String update = "UPDATE `usuarios` SET localidad = ?, provincia = ?, nacionalidad = ?, sexo = ?, nombre = ?, apellido = ?, fecha_Nacimiento  = ?, telefono = ?, mail = ?, direccion = ? WHERE dni=?";
	private static final String delete = "UPDATE `usuarios` SET estado = false WHERE dni = ?";
	
	@Override
	public boolean existeUsuario(String dni) {
		
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		boolean existeU = false;
		try {
			st = cn.getSQLConexion().prepareStatement(buscarDni);
			st.setString(1, dni);
			rs = st.executeQuery();
			if(rs.next()) {
				existeU = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existeU;
	}
	@Override
	public boolean comprobarContra(String dni, String contra) {
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		boolean loginExitoso = false;
		try {
			st = cn.getSQLConexion().prepareStatement(buscarContra);
			st.setString(1, dni);
			rs = st.executeQuery();
			if(rs.next()) {
				if(rs.getString("contra").equals(contra)) {
					loginExitoso = true;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return loginExitoso;
	}
	
	private Usuarios getUsuario(ResultSet rs) throws SQLException{
		TiposDeUsuariosDao tdudao = new TiposDeUsuariosDaoImpl();
		
		String dni = rs.getString("dni");
		TiposDeUsuarios tdu = tdudao.obtenerTiposDeUsuariosCod(rs.getString("cod_Tipo"));
		String localidad = rs.getString("localidad");
		String provincia = rs.getString("provincia");
		String nacionalidad =rs.getString("nacionalidad");
		String sexo = rs.getString("sexo");
		String nombre = rs.getString("nombre");
		String apellido = rs.getString("apellido");
		String fnac = rs.getString("fecha_Nacimiento");
		String telefono = rs.getString("telefono");
		String mail = rs.getString("mail");
		String direc = rs.getString("direccion");
		String contra = rs.getString("contra");
		boolean estado = rs.getBoolean("estado");
		
		
		return new Usuarios(dni, tdu, localidad, provincia, nacionalidad, sexo, nombre, apellido, fnac, telefono, mail, direc, contra, estado);
		
	}
	
	@Override
	public Usuarios obtenerUsuario(String dni) {
		Usuarios u = new Usuarios();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarDni);
			st.setString(1, dni);
			rs = st.executeQuery();
			if(rs.next()) {
				u = getUsuario(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	public boolean insertarUsuario(Usuarios usuario)
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, usuario.getDni());
			statement.setString(2, usuario.getTiposDeUsuarios().getCod_tipo());
			statement.setString(3, usuario.getLocalidad());
			statement.setString(4, usuario.getProvincia());
			statement.setString(5, usuario.getNacionalidad());
			statement.setString(6, usuario.getSexo());
			statement.setString(7, usuario.getNombre());
			statement.setString(8, usuario.getApellido());
			statement.setString(9, usuario.getFechaNacimiento());
			statement.setString(10, usuario.getTelefono());
			statement.setString(11, usuario.getMail());
			statement.setString(12, usuario.getDireccion());
			statement.setString(13, usuario.getContraseña());
			statement.setBoolean(14, true);
			if(statement.executeUpdate() > 0)
			{
				conexion.getSQLConexion().commit();;
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
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
	
	public boolean eliminarUsuario(Usuarios usuario)
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, usuario.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.getSQLConexion().commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	@Override
	public boolean modificarUsuario(Usuarios usuario) {
		PreparedStatement st;
		Conexion cn = Conexion.getConexion();
		boolean updateExitoso = false;
		
		try {
			st = cn.getSQLConexion().prepareStatement(update);
			st.setString(1, usuario.getLocalidad());
			st.setString(2, usuario.getProvincia());
			st.setString(3, usuario.getNacionalidad());
			st.setString(4, usuario.getSexo());
			st.setString(5, usuario.getNombre());
			st.setString(6, usuario.getApellido());
			st.setString(7, usuario.getFechaNacimiento());
			st.setString(8, usuario.getTelefono());
			st.setString(9, usuario.getMail());
			st.setString(10, usuario.getDireccion());
			st.setString(11, usuario.getDni());
			
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
