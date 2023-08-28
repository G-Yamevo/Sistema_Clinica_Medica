package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TiposDeUsuariosDao;
import entidad.TiposDeUsuarios;

public class TiposDeUsuariosDaoImpl implements TiposDeUsuariosDao{
	private static final String buscarTipoCod = "SELECT * FROM tipos_usuario WHERE cod_Tipo=?";
	private static final String buscarTipoDes = "SELECT * FROM tipos_usuario WHERE descripcion=?";
	private static final String readall = "SELECT * FROM tipos_usuario";
	
	@Override
	public TiposDeUsuarios obtenerTiposDeUsuariosCod(String cod) {
		TiposDeUsuarios tdu = new TiposDeUsuarios();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarTipoCod);
			st.setString(1, cod);
			rs = st.executeQuery();
			if(rs.next()) {
				tdu = getTiposDeUsuarios(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tdu;
	}
	
	public TiposDeUsuarios obtenerTiposDeUsuariosDes(String des) {
		TiposDeUsuarios tdu = new TiposDeUsuarios();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarTipoDes);
			st.setString(1, des);
			rs = st.executeQuery();
			if(rs.next()) {
				tdu = getTiposDeUsuarios(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tdu;
	}

	@Override
	public List<TiposDeUsuarios> readAll() {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<TiposDeUsuarios> tdu = new ArrayList<TiposDeUsuarios>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readall);
			rs = st.executeQuery();
			while(rs.next()) {
				tdu.add(getTiposDeUsuarios(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tdu;
	}
	
	public TiposDeUsuarios getTiposDeUsuarios(ResultSet rs) throws SQLException{
		String cod = rs.getString("cod_Tipo");
		String descripcion = rs.getString("descripcion");
		return new TiposDeUsuarios(cod, descripcion);
	}
	
}
