package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuarios;
import excepciones.UsuarioDadoDeBajaExcepcion;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{

	UsuarioDao ud = new UsuarioDaoImpl();
	
	@Override
	public boolean existeUsuario(String dni) {
		return ud.existeUsuario(dni);
	}
	public int comprobarLogin(String dni, String contra) {
        int control = 0;
        //Significados de los valores de control
        //0: El DNI ingresado no es correctos
        //1: La contraseña y el DNI son correctos
        //2: La contraseña es incorrecta
        try {
            obtenerUsuario(dni).verificarBajaUsuario();
            if(ud.existeUsuario(dni)) {
                if(ud.comprobarContra(dni, contra)){
                    control = 1;
                }
                else {
                    control = 2;
                }
            }

        }catch(UsuarioDadoDeBajaExcepcion e) {
        e.printStackTrace();
        }

        return control;
    }

	
	@Override
	public Usuarios obtenerUsuario(String dni) {
		return ud.obtenerUsuario(dni);
	}
	
	@Override
	public int comprobarTipoDeUsuario(String dni) {
		//Significados de los valores de tdu
		//0: El usuario existe pero fue dado de baja o no se pudo encontrar
		//1: El usuario es de tipo Administrador
		//2: El usuario es de tipo Medico
		//3: El usuario es de tipo Paciente
		int tdu = 0;
		//if(ud.obtenerUsuario(dni).isEstado()) {
			if(obtenerUsuario(dni).getTiposDeUsuarios().getCod_tipo().equals("1")) {
				tdu = 1;
			}
			if(obtenerUsuario(dni).getTiposDeUsuarios().getCod_tipo().equals("2")) {
				tdu = 2;
			}
			if(obtenerUsuario(dni).getTiposDeUsuarios().getCod_tipo().equals("3")) {
				tdu = 3;
			}
		//}
		
		return tdu;
	}

	@Override
	public boolean insertarUsuario(Usuarios usuario) {
		return ud.insertarUsuario(usuario);
	}

	@Override
	public boolean eliminarUsuario(Usuarios usuario) {
		return ud.eliminarUsuario(usuario);
	}
	

}
