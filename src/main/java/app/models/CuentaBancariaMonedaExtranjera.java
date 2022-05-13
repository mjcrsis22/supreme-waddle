package app.models;

import java.time.LocalDate;
import java.util.Set;

public class CuentaBancariaMonedaExtranjera extends CuentaBancaria {
	private MonedaExtranjera monedaAsociada;

	public CuentaBancariaMonedaExtranjera() {
		super();
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcortado, LocalDate fechaCierre, Cliente titular,
			Set<Cliente> cotitulares, Set<Movimiento> movimientosRealizados) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcortado, fechaCierre, titular,
				cotitulares, movimientosRealizados);
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcortado, LocalDate fechaCierre, Cliente titular) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcortado, fechaCierre, titular);
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcortado, LocalDate fechaCierre, Cliente titular,
			Set<Cliente> cotitulares, Set<Movimiento> movimientosRealizados, MonedaExtranjera monedaAsociada) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcortado, fechaCierre, titular,
				cotitulares, movimientosRealizados);
		this.monedaAsociada = monedaAsociada;
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcortado, LocalDate fechaCierre, Cliente titular,
			MonedaExtranjera monedaAsociada) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcortado, fechaCierre, titular);
		this.monedaAsociada = monedaAsociada;
	}

	public MonedaExtranjera getMonedaAsociada() {
		return monedaAsociada;
	}

	public void setMonedaAsociada(MonedaExtranjera monedaAsociada) {
		this.monedaAsociada = monedaAsociada;
	}

}
