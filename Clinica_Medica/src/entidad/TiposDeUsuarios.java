package entidad;

public class TiposDeUsuarios {
	private String cod_tipo;
	private String descripcion;
	
	public TiposDeUsuarios() {};
	
	public TiposDeUsuarios(String cod_tipo, String descripcion) {
		this.cod_tipo = cod_tipo;
		this.descripcion = descripcion;
	}

	public String getCod_tipo() {
		return cod_tipo;
	}

	public void setCod_tipo(String cod_tipo) {
		this.cod_tipo = cod_tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
