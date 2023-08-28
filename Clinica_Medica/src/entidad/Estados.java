package entidad;

public class Estados {
	private String cod_estado;
	private String descripcion;
	
	public Estados() {};
	
	public Estados(String cod_estado, String descripcion) {
		this.cod_estado = cod_estado;
		this.descripcion = descripcion;
	}

	public String getCod_estado() {
		return cod_estado;
	}

	public void setCod_estado(String cod_estado) {
		this.cod_estado = cod_estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
