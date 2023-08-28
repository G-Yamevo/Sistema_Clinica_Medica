package dao;

import entidad.Usuarios;

public interface UsuarioDao {
	public boolean existeUsuario(String dni);
	public boolean comprobarContra(String dni, String contra);
	public Usuarios obtenerUsuario(String dni);
	public boolean insertarUsuario(Usuarios usuario);
	public boolean modificarUsuario(Usuarios usuario);
	public boolean eliminarUsuario(Usuarios usuario);
}
