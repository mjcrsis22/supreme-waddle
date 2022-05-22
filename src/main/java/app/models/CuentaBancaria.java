package app.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import app.dtos.implementations.ResultadoTransferenciaImpl;

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
	private Double descubiertoAcordado;
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
		// TODO: ??
	}

	public void addMovimiento(Movimiento movimiento) {
		this.movimientosRealizados.add(movimiento);
	}

	public ResultadoTransferenciaImpl realizarTransferencia(Double monto, CuentaBancaria cuentaDestino)
			throws Exception {

		// Las cuentas deben estar abiertas para realizar esta operación
		if (this.fechaCierre != null) {
			throw new Exception("La cuenta originante se encuentra cerrada.");
		}
		if (cuentaDestino.getFechaCierre() != null) {
			throw new Exception("La cuenta destino se encuentra cerrada.");
		}

		// Por el momento solo se puede transferir a cuentas con la misma moneda
		if (this.getMoneda() != cuentaDestino.getMoneda()) {
			throw new Exception("Por el momento solo se admiten transferencias entre cuentas con la misma moneda.");
		}

		// La cuenta originante debe tener saldo suficiente
		if ((this.saldoActual + this.descubiertoAcordado) < monto) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		this.saldoActual = this.saldoActual - monto;
		cuentaDestino.setSaldoActual(cuentaDestino.getSaldoActual() + monto);

		MovimientoTransferenciaRealizada mov1 = new MovimientoTransferenciaRealizada(LocalDateTime.now(), -monto, "",
				this);
		MovimientoTransferenciaRecibida mov2 = new MovimientoTransferenciaRecibida(LocalDateTime.now(), monto, "",
				cuentaDestino);

		this.addMovimiento(mov1);
		cuentaDestino.addMovimiento(mov2);

		return new ResultadoTransferenciaImpl(mov1, mov2);
	}

}
