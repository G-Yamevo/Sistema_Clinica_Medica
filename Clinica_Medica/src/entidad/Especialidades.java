package entidad;

public class Especialidades {
	private String cod_especialidad;
	private String descripcion;
	
	public Especialidades() {};
	public Especialidades(String cod_especialidad, String descripcion) {
		this.cod_especialidad = cod_especialidad;
		this.descripcion = descripcion;
	}

	public String getCod_especialidad() {
		return cod_especialidad;
	}

	public void setCod_especialidad(String cod_especialidad) {
		this.cod_especialidad = cod_especialidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
