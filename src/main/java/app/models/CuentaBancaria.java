package app.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity(name = "T_CUENTABANCARIA")
@DiscriminatorColumn(name = "disc", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "CB")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "cuentabancaria.findAll", query = "SELECT CB FROM T_CUENTABANCARIA CB")
public abstract class CuentaBancaria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, updatable = false, unique = true)
	@NotNull(message = "{common.generic.notEmpty}")
	private Long nroCuenta;
	@Column(nullable = false, updatable = false)
	@NotNull(message = "{common.generic.notEmpty}")
	@PastOrPresent(message = "{common.generic.pastOrPresent}")
	private LocalDate fechaCreacion;
	@Column(nullable = false, updatable = false)
	@NotNull(message = "{common.generic.notEmpty}")
	private Double saldoInicial;
	@Column(nullable = false)
	@NotNull(message = "{common.generic.notEmpty}")
	private Double saldoActual;
	@Column(nullable = false)
	@NotNull(message = "{common.generic.notEmpty}")
	private Double descubiertoAcordado;
	@Column()
	@PastOrPresent(message = "{common.generic.pastOrPresent}")
	private LocalDate fechaCierre;

	@ManyToOne(optional = false)
	@JoinColumn(updatable = false)
	@NotNull(message = "{common.generic.notEmpty}")
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
			Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular) {
		super();
		this.nroCuenta = nroCuenta;
		this.fechaCreacion = fechaCreacion;
		this.saldoInicial = saldoInicial;
		this.saldoActual = saldoActual;
		this.descubiertoAcordado = descubiertoAcordado;

		try {
			this.setTitularidad(titular);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.fechaCierre = fechaCierre;
	}

	public CuentaBancaria(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial, Double saldoActual,
			Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular, Set<Cliente> cotitulares,
			Set<Movimiento> movimientosRealizados) {
		super();
		this.nroCuenta = nroCuenta;
		this.fechaCreacion = fechaCreacion;
		this.saldoInicial = saldoInicial;
		this.saldoActual = saldoActual;
		this.descubiertoAcordado = descubiertoAcordado;

		try {
			this.setTitularidad(titular);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Cliente cliente : cotitulares) {
			try {
				this.addCotitularidad(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.movimientosRealizados = movimientosRealizados;

		this.fechaCierre = fechaCierre;
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

	public Double getdescubiertoAcordado() {
		return descubiertoAcordado;
	}

	public void setdescubiertoAcordado(Double descubiertoAcordado) {
		this.descubiertoAcordado = descubiertoAcordado;
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
	public abstract Moneda getMoneda();

	public boolean clienteEsTitularOCotitular(Cliente cliente) {
		return this.titular == cliente || this.cotitulares.contains(cliente);
	}

	public void setTitularidad(Cliente cliente) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.fechaCierre != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		// El cliente no debe ser titular o cotitular de la cuenta
		if (this.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("El cliente ya es titular o cotitular de esta cuenta.");
		}

		this.titular = cliente;
		cliente.addTitularidadCuenta(this);
	}

	public void addCotitularidad(Cliente cliente) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.fechaCierre != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		// El cliente no debe ser titular o cotitular de la cuenta
		if (this.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("El cliente ya es titular o cotitular de esta cuenta.");
		}

		this.cotitulares.add(cliente);
		cliente.addCotitularidadCuenta(this);
	}

	public void removeCotitularidad(Cliente cliente) {
		// TODO: se debe validar si la cuenta se encuentra abierta?
		// o si el cliente no figura como cotitular?
		this.cotitulares.remove(cliente);
	}

	public void addMovimiento(Movimiento movimiento) throws Exception {
		if (movimiento.ejecutar(this)) {
			this.movimientosRealizados.add(movimiento);
		}
	}

	public abstract boolean aplicarMovimiento(MovimientoCompraMonedaExtranjera movimiento) throws Exception;

	public abstract boolean aplicarMovimiento(MovimientoVentaMonedaExtranjera movimiento) throws Exception;

	public boolean aplicarMovimiento(MovimientoDeposito movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		this.saldoActual += movimiento.getMonto();

		return true;
	}

	public boolean aplicarMovimiento(MovimientoExtraccion movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		// La cuenta debe tener saldo suficiente
		if ((this.getSaldoActual() + this.getdescubiertoAcordado()) < movimiento.getMonto()) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		this.saldoActual -= movimiento.getMonto();

		return true;
	}

	public boolean aplicarMovimiento(MovimientoTransferenciaRealizada movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		// La cuenta originante debe tener saldo suficiente
		if ((this.getSaldoActual() + this.getdescubiertoAcordado()) < movimiento.getMonto()) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		this.saldoActual -= movimiento.getMonto();

		return true;
	}

	public boolean aplicarMovimiento(MovimientoTransferenciaRecibida movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		this.saldoActual += movimiento.getMonto();

		return true;
	}

}
