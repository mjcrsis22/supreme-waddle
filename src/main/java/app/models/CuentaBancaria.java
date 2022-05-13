package app.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class CuentaBancaria {
	private Long nroCuenta;
	private LocalDate fechaCreacion;
	private Double saldoInicial;
	private Double saldoActual;
	private Double descubiertoAcortado;
	private LocalDate fechaCierre;

	private Cliente titular;
	private Set<Cliente> cotitulares = new HashSet<Cliente>();
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
