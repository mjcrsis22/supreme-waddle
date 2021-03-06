package app.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * La clase Cliente representa y maneja los metodos asociados a un cliente del
 * minibanco.
 * 
 * Ser?n relacionados como titulares o cotitulares de las
 * {@link app.models.CuentaBancaria}.
 * 
 * @author Marcos Colina
 */
@Entity(name = "T_CLIENTE")
@NamedQueries({ @NamedQuery(name = Cliente.findAllNamedQuery, query = "SELECT C FROM T_CLIENTE C"),
		@NamedQuery(name = Cliente.findByNameNamedQuery, query = "SELECT C FROM T_CLIENTE C WHERE C.nombre LIKE :nombreCliente") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente {

	public static final String findAllNamedQuery = "cliente.findAll";
	public static final String findByNameNamedQuery = "cliente.findByName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String nombre;
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Size(min = 2, max = 50, message = "{common.generic.size}")
	private String apellido;
	@Embedded
	private Direccion direccion;
	@Column(length = 14)
	@Size(min = 8, max = 14, message = "{common.generic.size}")
	private String telefono;
	@Column()
	@Email(message = "{common.generic.email}")
	private String email;

	/**
	 * Lista de {@link app.models.CuentaBancaria} a las que el
	 * {@link app.models.Cliente} se relaciona como titular.
	 * 
	 * Un cliente no debe relacionarse a una misma cuenta como titular y cotitular,
	 * esto ser? controlado por el modelo {@link app.models.CuentaBancaria}.
	 */
	@OneToMany(mappedBy = "titular")
	private Set<CuentaBancaria> cuentasTitular = new HashSet<CuentaBancaria>();

	/**
	 * Lista de {@link app.models.CuentaBancaria} a las que el
	 * {@link app.models.Cliente} se relaciona como cotitular.
	 * 
	 * Un cliente no debe relacionarse a una misma cuenta como titular y cotitular,
	 * esto ser? controlado por el modelo {@link app.models.CuentaBancaria}.
	 */
	@ManyToMany(mappedBy = "cotitulares")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
