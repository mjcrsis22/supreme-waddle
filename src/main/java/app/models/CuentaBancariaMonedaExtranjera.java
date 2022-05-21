package app.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity(name = "T_CUENTABANCARIA_MONEDAEXTRANJERA")
public class CuentaBancariaMonedaExtranjera extends CuentaBancaria {
	@Column(nullable = false)
	@Convert(converter = MonedaExtranjeraConverter.class)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private MonedaExtranjera monedaAsociada;

	public CuentaBancariaMonedaExtranjera() {
		super();
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular,
			Set<Cliente> cotitulares, Set<Movimiento> movimientosRealizados) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre, titular,
				cotitulares, movimientosRealizados);
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre, titular);
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular,
			Set<Cliente> cotitulares, Set<Movimiento> movimientosRealizados, MonedaExtranjera monedaAsociada) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre, titular,
				cotitulares, movimientosRealizados);
		this.monedaAsociada = monedaAsociada;
	}

	public CuentaBancariaMonedaExtranjera(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular,
			MonedaExtranjera monedaAsociada) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre, titular);
		this.monedaAsociada = monedaAsociada;
	}

	public MonedaExtranjera getMonedaAsociada() {
		return monedaAsociada;
	}

	public void setMonedaAsociada(MonedaExtranjera monedaAsociada) {
		this.monedaAsociada = monedaAsociada;
	}

	// TODO: Metodos especiales
	@Override
	public Moneda getMoneda() {
		return monedaAsociada;
	}

}
