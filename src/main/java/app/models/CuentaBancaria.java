package app.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

@Entity(name = "T_CUENTABANCARIA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CuentaBancaria {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, updatable = false, unique = true)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private Long nroCuenta;
	@Column(nullable = false, updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@PastOrPresent(message = "{common.generic.pastOrPresent}")
	private LocalDate fechaCreacion;
	@Column(nullable = false, updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private Double saldoInicial;
	@Column(nullable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private Double saldoActual;
	@Column(nullable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private Double descubiertoAcortado;
	@Column()
	@PastOrPresent(message = "{common.generic.pastOrPresent}")
	private LocalDate fechaCierre;

	@ManyToOne(optional = false)
	@JoinColumn(updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private Cliente titular;
	@ManyToMany()
	@JoinTable(name = "T_CUENTABANCARIA_R_COTITULAR")
	private Set<Cliente> cotitulares = new HashSet<Cliente>();
	@OneToMany()
	@JoinTable(name = "T_CUENTABANCARIA_R_MOVIMIENTO")
	private Set<Movimiento> movimientosRealizados = new HashSet<Movimiento>();

	public CuentaBancaria() {
	}

	public CuentaBancaria(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial, Double saldoActual,
			Double descubiertoAcortado, LocalDate fechaCierre, Cliente titular) {
		super();
		this.nroCuenta = nroCuenta;
		this.fechaCreacion = fechaCreacion;
		this.saldoInicial = saldoInicial;
		this.saldoActual = saldoActual;
		this.descubiertoAcortado = descubiertoAcortado;
		this.fechaCierre = fechaCierre;

		this.setTitularidad(titular);
	}

	public CuentaBancaria(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial, Double saldoActual,
			Double descubiertoAcortado, LocalDate fechaCierre, Cliente titular, Set<Cliente> cotitulares,
			Set<Movimiento> movimientosRealizados) {
		super();
		this.nroCuenta = nroCuenta;
		this.fechaCreacion = fechaCreacion;
		this.saldoInicial = saldoInicial;
		this.saldoActual = saldoActual;
		this.descubiertoAcortado = descubiertoAcortado;
		this.fechaCierre = fechaCierre;

		this.setTitularidad(titular);

		for (Cliente cliente : cotitulares) {
			this.addCotitularidad(cliente);
		}

		this.movimientosRealizados = movimientosRealizados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(Long nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getDescubiertoAcortado() {
		return descubiertoAcortado;
	}

	public void setDescubiertoAcortado(Double descubiertoAcortado) {
		this.descubiertoAcortado = descubiertoAcortado;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDate fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public Set<Cliente> getCotitulares() {
		return cotitulares;
	}

	public void setCotitulares(Set<Cliente> cotitulares) {
		this.cotitulares = cotitulares;
	}

	public Set<Movimiento> getMovimientosRealizados() {
		return movimientosRealizados;
	}

	public void setMovimientosRealizados(Set<Movimiento> movimientosRealizados) {
		this.movimientosRealizados = movimientosRealizados;
	}

	// TODO: Metodos especiales
	public void setTitularidad(Cliente titular) {
		this.titular = titular;
		titular.addTitularidadCuenta(this);
	}

	public void addCotitularidad(Cliente cotitular) {
		this.cotitulares.add(cotitular);
		cotitular.addCotitularidadCuenta(this);
	}

	public void addMovimiento(Movimiento movimiento) {
		this.movimientosRealizados.add(movimiento);
	}

}
