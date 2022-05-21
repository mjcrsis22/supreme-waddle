package app.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;

@Entity(name = "T_CUENTABANCARIA_MONEDANACIONAL")
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

}
