package app.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "T_CUENTABANCARIA_MONEDANACIONAL")
@DiscriminatorValue(value = "CB_MONEDANACIONAL")
public class CuentaBancariaMonedaNacional extends CuentaBancaria {

	public CuentaBancariaMonedaNacional() {
		super();
	}

	public CuentaBancariaMonedaNacional(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular,
			Set<Cliente> cotitulares, Set<Movimiento> movimientosRealizados) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre, titular,
				cotitulares, movimientosRealizados);
	}

	public CuentaBancariaMonedaNacional(Long nroCuenta, LocalDate fechaCreacion, Double saldoInicial,
			Double saldoActual, Double descubiertoAcordado, LocalDate fechaCierre, Cliente titular) {
		super(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre, titular);
	}

	// TODO: Metodos especiales
	@Override
	public Moneda getMoneda() {
		return MonedaNacional.ARS;
	}

	@Override
	public boolean aplicarMovimiento(MovimientoCompraMonedaExtranjera movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		// La cuenta en moneda nacional debe tener saldo suficiente
		if (this.getSaldoActual() < movimiento.getMonto()) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		this.setSaldoActual(this.getSaldoActual() - movimiento.getMonto());
		return true;
	}

	@Override
	public boolean aplicarMovimiento(MovimientoVentaMonedaExtranjera movimiento) throws Exception {

		// La cuenta debe estar abierta para realizar esta operación
		if (this.getFechaCierre() != null) {
			throw new Exception("La cuenta se encuentra cerrada.");
		}

		this.setSaldoActual(this.getSaldoActual() + movimiento.getMonto());
		return true;
	}

}
