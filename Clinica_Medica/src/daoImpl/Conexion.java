package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "admin";
	private String pass = "admin";
	private String bd = "bdClinica";
	
	public static Conexion instancia;
	private Connection cn;
	
	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.cn = DriverManager.getConnection(host+bd,user,pass);
			this.cn.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion() {
		if(instancia == null){
			instancia = new Conexion();
		}
		return instancia;
	}
	
	public Connection getSQLConexion() {
		return this.cn;
	}
	
	public void cerrarConexion() {
		try {
			this.cn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		instancia = null;
	}
}