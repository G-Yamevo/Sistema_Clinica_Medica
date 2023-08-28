package negocio;

import entidad.Usuarios;

public interface UsuarioNegocio {
	public boolean existeUsuario(String dni);
	public int comprobarLogin(String dni, String contra);
	public Usuarios obtenerUsuario(String dni);
	public int comprobarTipoDeUsuario(String dni);
	public boolean insertarUsuario(Usuarios usuario);
	public boolean eliminarUsuario(Usuarios usuario);
}
