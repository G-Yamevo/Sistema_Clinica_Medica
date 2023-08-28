package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadesDao;
import entidad.Especialidades;

public class EspecialidadesDaoImpl implements EspecialidadesDao{
	private static final String buscarEs = "SELECT * FROM especialidades WHERE cod_Especialidad=?";
	private static final String readall = "SELECT * FROM especialidades";
	
	@Override
	public Especialidades obtenerEspecialidad(String cod) {
		Especialidades es = new Especialidades();
		PreparedStatement st;
		ResultSet rs;
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(buscarEs);
			st.setString(1, cod);
			rs = st.executeQuery();
			if(rs.next()) {
				es = getEspecialidad(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return es;
	}

	@Override
	public List<Especialidades> readAll() {
		PreparedStatement st;
		ResultSet rs;
		ArrayList<Especialidades> es = new ArrayList<Especialidades>();
		Conexion cn = Conexion.getConexion();
		try {
			st = cn.getSQLConexion().prepareStatement(readall);
			rs = st.executeQuery();
			while(rs.next()) {
				es.add(getEspecialidad(rs));
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return es;
	}
	
	public Especialidades getEspecialidad(ResultSet rs) throws SQLException{
		String cod = rs.getString("cod_especialidad");
		String descripcion = rs.getString("descripcion");
		return new Especialidades(cod, descripcion);
	}

}
