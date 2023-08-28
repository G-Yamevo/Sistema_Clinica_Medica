package entidad;

public class Turnos {
	private String cod_turno;
	private String dni_medico;
	private String dni_paciente;
	private String cod_especialidad;
	private Estados estado;
	private String dia;
	private String hora;
	
	public Turnos() {};
	
	public Turnos(String cod_turno, String dni_medico, String dni_paciente, String cod_especialidad, Estados estado,
			String dia, String hora) {
		super();
		this.cod_turno = cod_turno;
		this.dni_medico = dni_medico;
		this.dni_paciente = dni_paciente;
		this.cod_especialidad = cod_especialidad;
		this.estado = estado;
		this.dia = dia;
		this.hora = hora;
	}

	public String getCod_turno() {
		return cod_turno;
	}

	public void setCod_turno(String cod_turno) {
		this.cod_turno = cod_turno;
	}

	public String getDni_medico() {
		return dni_medico;
	}

	public void setDni_medico(String dni_medico) {
		this.dni_medico = dni_medico;
	}

	public String getDni_paciente() {
		return dni_paciente;
	}

	public void setDni_paciente(String dni_paciente) {
		this.dni_paciente = dni_paciente;
	}

	public String getCod_especialidad() {
		return cod_especialidad;
	}

	public void setCod_especialidad(String cod_especialidad) {
		this.cod_especialidad = cod_especialidad;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Turnos [cod_turno=" + cod_turno + ", dni_medico=" + dni_medico + ", dni_paciente=" + dni_paciente
				+ ", cod_especialidad=" + cod_especialidad + ", estado=" + estado + ", dia=" + dia + ", hora=" + hora
				+ "]";
	}
	
	
}
