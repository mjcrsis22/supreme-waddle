package app.models;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

public class CuentaBancariaTest {
	// TODO: para validar todos los atributos a la vez, se pueden definir matchers

	@Test
	public void CuentaBancaria_SePuedeContruirVacio() {
		// given
		CuentaBancaria sut;

		// when
		sut = new CuentaBancariaMonedaNacional();

		// then
		String msg = "El constructor vacio no setea ningun atributo";
		Object[] expected = { null, null, null, null, null, null, null };
		Object[] actual = { sut.getNroCuenta(), sut.getFechaCreacion(), sut.getSaldoInicial(), sut.getSaldoActual(),
				sut.getdescubiertoAcordado(), sut.getFechaCierre(), sut.getTitular() };
		assertArrayEquals(msg, expected, actual);
	}

	@Test
	public void CuentaBancaria_SePuedeContruirSinListas() {
		// given
		CuentaBancaria sut;
		Long nroCuenta = 1L;
		LocalDate fechaCreacion = LocalDate.of(2010, 05, 13);
		Double saldoInicial = 0.00;
		Double saldoActual = 150000.84;
		Double descubiertoAcordado = 0.00;
		LocalDate fechaCierre = null;
		Cliente titular = Mockito.mock(Cliente.class);

		// when
		sut = new CuentaBancariaMonedaNacional(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado,
				fechaCierre, titular);

		// then
		String msg = "El constructor vacio no setea ningun atributo";
		Object[] expected = { nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre,
				titular };
		Object[] actual = { sut.getNroCuenta(), sut.getFechaCreacion(), sut.getSaldoInicial(), sut.getSaldoActual(),
				sut.getdescubiertoAcordado(), sut.getFechaCierre(), sut.getTitular() };
		assertArrayEquals(msg, expected, actual);
	}

	@Test
	public void CuentaBancaria_SePuedeContruirConListas() {
		// las cuentas pueden tener 1 titular y cotitulares.
		// cada cuenta puede tener varios movimientos.

		// given
		CuentaBancaria sut;
		Long nroCuenta = 1L;
		LocalDate fechaCreacion = LocalDate.of(2010, 05, 13);
		Double saldoInicial = 0.00;
		Double saldoActual = 150000.84;
		Double descubiertoAcordado = 0.00;
		LocalDate fechaCierre = null;
		Cliente titular = Mockito.mock(Cliente.class);

		Set<Cliente> cotitulares = new HashSet<Cliente>();
		cotitulares.add(Mockito.mock(Cliente.class));
		cotitulares.add(Mockito.mock(Cliente.class));

		Set<Movimiento> movimientosRealizados = new HashSet<Movimiento>();
		movimientosRealizados.add(Mockito.mock(Movimiento.class));
		movimientosRealizados.add(Mockito.mock(Movimiento.class));

		// when
		sut = new CuentaBancariaMonedaNacional(nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado,
				fechaCierre, titular, cotitulares, movimientosRealizados);

		// then
		String msg = "El constructor vacio no setea ningun atributo";
		Object[] expected = { nroCuenta, fechaCreacion, saldoInicial, saldoActual, descubiertoAcordado, fechaCierre,
				titular, cotitulares, movimientosRealizados };
		Object[] actual = { sut.getNroCuenta(), sut.getFechaCreacion(), sut.getSaldoInicial(), sut.getSaldoActual(),
				sut.getdescubiertoAcordado(), sut.getFechaCierre(), sut.getTitular(), sut.getCotitulares(),
				sut.getMovimientosRealizados() };
		assertArrayEquals(msg, expected, actual);
	}

}
