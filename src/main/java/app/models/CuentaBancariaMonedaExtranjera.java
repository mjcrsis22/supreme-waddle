package app.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity(name = "T_CUENTABANCARIA_MONEDAEXTRANJERA")
@DiscriminatorValue(value = "CB_MONEDAEXTRANJERA")
@NamedQuery(name = "cuentabancaria.findByCurrency", query = "SELECT CB FROM T_CUENTABANCARIA CB LEFT JOIN T_CUENTABANCARIA_MONEDAEXTRANJERA CBE  ON  CBE.id = CB.id WHERE CBE.monedaAsociada = :monedaAsociada")
public class CuentaBancariaMonedaExtranjera extends CuentaBancaria {
	@Column(nullable = false)
	@Convert(converter = MonedaExtranjeraConverter.class)
	@NotNull(message = "{common.generic.notEmpty}")
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

	@Override
	public boolean aplicarMovimiento(MovimientoCompraMonedaExtranjera movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		this.setSaldoActual(this.getSaldoActual() + movimiento.getMonto());
		return true;
	}

	@Override
	public boolean aplicarMovimiento(MovimientoVentaMonedaExtranjera movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		// La cuenta en moneda extranjera debe tener saldo suficiente
		if (this.getSaldoActual() < movimiento.getMonto()) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		this.setSaldoActual(this.getSaldoActual() - movimiento.getMonto());
		return true;
	}

}
