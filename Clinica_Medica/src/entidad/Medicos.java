package entidad;

import java.time.LocalDate;
import excepciones.HorarioAtencionExcepcion;;

public class Medicos extends Usuarios{
	private Especialidades especialidad;
	private String diasAtencion;
	private String horariosAtencion;
	
	public Medicos() {};
	
	public Medicos(String dni, TiposDeUsuarios tipo, String localidad, String provincia, String nacionalidad,
			String sexo, String nombre, String apellido, String fechaNacimiento, String telefono, String mail,
			String direccion, String contraseña, boolean estado, Especialidades especialidad, String diasAtencion,
	String horariosAtencion) {
		super(dni, tipo, localidad, provincia, nacionalidad, sexo, nombre, apellido, fechaNacimiento, telefono, mail, direccion,
				contraseña, estado);
		this.especialidad = especialidad;
		this.diasAtencion = diasAtencion;
		this.horariosAtencion = horariosAtencion;
	}
	
	public Especialidades getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidades especialidad) {
		this.especialidad = especialidad;
	}

	public String getDiasAtencion() {
		return diasAtencion;
	}

	public void setDiasAtencion(String diasAtencion) {
		this.diasAtencion = diasAtencion;
	}

	public String getHorariosAtencion() {
		return horariosAtencion;
	}

	public void setHorariosAtencion(String horariosAtencion) {
		
		if(validarHorario(horariosAtencion)==true) {
			this.horariosAtencion = horariosAtencion;
		}
		
	}

	@Override
	public String toString() {
		return "Nombre: " + getNombre() + " DNI: "+ getDni() + " Sexo: " + getSexo() + " Especialidad: " + getEspecialidad();
	}
	
	
	public static boolean validarHorario(String horario) throws HorarioAtencionExcepcion {
		
		String h1=horario.substring(0,2);
		String h2=horario.substring(8,10);
		
		int i1=Integer.parseInt(h1);
		int i2=Integer.parseInt(h2);
		
		if(i1>=i2) {
			HorarioAtencionExcepcion Excepcion= new HorarioAtencionExcepcion();
			throw Excepcion;
		}
		
		return true;
	}
	
}
