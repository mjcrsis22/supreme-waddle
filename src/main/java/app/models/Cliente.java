package app.models;

import java.util.HashSet;
import java.util.Set;

public class Cliente {
	private String nombre;
	private String apellido;
	private Direccion direccion;
	private String telefono;
	private String email;

	private Set<CuentaBancaria> cuentasTitular = new HashSet<CuentaBancaria>();
	private Set<CuentaBancaria> cuentasCotitular = new HashSet<CuentaBancaria>();

	public Cliente() {
	}

	public Cliente(String nombre, String apellido, Direccion direccion, String telefono, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public Cliente(String nombre, String apellido, Direccion direccion, String telefono, String email,
			Set<CuentaBancaria> cuentasTitular, Set<CuentaBancaria> cuentasCotitular) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.cuentasTitular = cuentasTitular;
		this.cuentasCotitular = cuentasCotitular;
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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<CuentaBancaria> getCuentasTitular() {
		return cuentasTitular;
	}

	public void setCuentasTitular(Set<CuentaBancaria> cuentasTitular) {
		this.cuentasTitular = cuentasTitular;
	}

	public Set<CuentaBancaria> getCuentasCotitular() {
		return cuentasCotitular;
	}

	public void setCuentasCotitular(Set<CuentaBancaria> cuentasCotitular) {
		this.cuentasCotitular = cuentasCotitular;
	}

	// TODO: Metodos especiales
	public void addTitularidadCuenta(CuentaBancaria cuenta) {
		this.cuentasTitular.add(cuenta);
	}

	public void addCotitularidadCuenta(CuentaBancaria cuenta) {
		this.cuentasCotitular.add(cuenta);
	}

}
