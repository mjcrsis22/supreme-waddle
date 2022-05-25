package app.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Embeddable
public class Direccion {
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String calle;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String numeroCivico;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String departamento;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String piso;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String ciudad;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String codigoPostal;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String provincia;

	public Direccion() {
	}

	public Direccion(String calle, String numeroCivico, String departamento, String piso, String ciudad,
			String codigoPostal, String provincia) {
		super();
		this.calle = calle;
		this.numeroCivico = numeroCivico;
		this.departamento = departamento;
		this.piso = piso;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
