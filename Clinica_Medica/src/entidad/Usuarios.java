package entidad;

import java.time.LocalDate;

import excepciones.UsuarioDadoDeBajaExcepcion;

public class Usuarios {
	private String dni;
	private TiposDeUsuarios tipo;
	private String localidad;
	private String provincia;
	private String nacionalidad;
	private String sexo;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String telefono;
	private String mail;
	private String direccion;
	private String contraseña;
	private boolean estado;
	
	public Usuarios() {};

	public Usuarios(String dni, TiposDeUsuarios tipo, String localidad, String provincia, String nacionalidad,
			String sexo, String nombre, String apellido, String fechaNacimiento, String telefono, String mail,
			String direccion, String contraseña, boolean estado) {
		this.dni = dni;
		this.tipo = tipo;
		this.localidad = localidad;
		this.provincia = provincia;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.mail = mail;
		this.direccion = direccion;
		this.contraseña = contraseña;
		this.estado = estado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContraseña() {
		return contraseña;
	}
	
	public TiposDeUsuarios getTiposDeUsuarios() {
		return tipo;
	}
	
	public void setTiposDeUsuarios(TiposDeUsuarios tipo) {
		this.tipo = tipo;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public boolean verificarBajaUsuario() throws UsuarioDadoDeBajaExcepcion {
        if(!estado) {
            UsuarioDadoDeBajaExcepcion e = new UsuarioDadoDeBajaExcepcion();
            throw e;
        }
        return true;
    }
	
}
