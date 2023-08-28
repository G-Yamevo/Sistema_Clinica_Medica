package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EstadosDao;
import entidad.Estados;

public class EstadosDaoImpl implements EstadosDao{
	private static final String buscarEs = "SELECT * FROM estados WHERE cod_Estado =?";
	private static final String readall = "SELECT * FROM estados";
	@Override
	public Estados obtenerEstado(String cod) {
		Estados es = new Estados();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarEs);
			st.setString(1, cod);
			rs = st.executeQuery();
			if(rs.next()) {
				es = getEstados(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return es;
	}

	@Override
	public List<Estados> readAll() {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Estados> les = new ArrayList<Estados>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readall);
			rs = st.executeQuery();
			while(rs.next()) {
				les.add(getEstados(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return les;
	}
	public Estados getEstados(ResultSet rs) throws SQLException{
		String cod = rs.getString("cod_Estado");
		String des = rs.getString("descripcion");
		return new Estados(cod, des);
	}
	
}
