package app.models;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

public class ClienteTest {

	@Test
	public void Cliente_SePuedeContruirVacio() {
		// given
		Cliente sut;

		// when
		sut = new Cliente();

		// then
		String msg = "El constructor vacio no setea ningun atributo";
		Object[] expected = { null, null, null, null, null };
		Object[] actual = { sut.getNombre(), sut.getApellido(), sut.getDireccion(), sut.getTelefono(), sut.getEmail() };
		assertArrayEquals(msg, expected, actual);
	}

	@Test
	public void Cliente_SePuedeContruirSinListas() {
		// given
		Cliente sut;
		String nombre = "José";
		String apellido = "Castillo";
		Direccion direccion = Mockito.mock(Direccion.class);
		String telefono = "11 12312312";
		String email = "123@123.com";

		// when
		sut = new Cliente(nombre, apellido, direccion, telefono, email);

		// then
		String msg = "El constructor con valores basicos, setea dichos atributos";
		Object[] expected = { nombre, apellido, direccion, telefono, email };
		Object[] actual = { sut.getNombre(), sut.getApellido(), sut.getDireccion(), sut.getTelefono(), sut.getEmail() };
		assertArrayEquals(msg, expected, actual);
	}

	@Test
	public void Cliente_SePuedeContruirConListas() {
		// TODO: los clientes pueden ser titulares y cotitulares de varias cuentas.

		// given
		Cliente sut;
		String nombre = "José";
		String apellido = "Castillo";
		Direccion direccion = Mockito.mock(Direccion.class);
		String telefono = "11 12312312";
		String email = "123@123.com";
		Set<CuentaBancaria> cuentasTitular = new HashSet<CuentaBancaria>();
		cuentasTitular.add(Mockito.mock(CuentaBancaria.class));
		cuentasTitular.add(Mockito.mock(CuentaBancaria.class));
		Set<CuentaBancaria> cuentasCotitular = new HashSet<CuentaBancaria>();
		cuentasCotitular.add(Mockito.mock(CuentaBancaria.class));
		cuentasCotitular.add(Mockito.mock(CuentaBancaria.class));
		cuentasCotitular.add(Mockito.mock(CuentaBancaria.class));

		// when
		sut = new Cliente(nombre, apellido, direccion, telefono, email, cuentasTitular, cuentasCotitular);

		// then
		String msg = "El constructor con todos los valores, setea dichos atributos";
		Object[] expected = { nombre, apellido, direccion, telefono, email, cuentasTitular, cuentasCotitular };
		Object[] actual = { sut.getNombre(), sut.getApellido(), sut.getDireccion(), sut.getTelefono(), sut.getEmail(),
				sut.getCuentasTitular(), sut.getCuentasCotitular() };
		assertArrayEquals(msg, expected, actual);
	}

}
