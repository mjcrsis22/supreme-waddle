package app.services.implementations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.ClienteDao;
import app.daos.interfaces.CuentaBancariaDao;
import app.models.Cliente;
import app.models.CuentaBancaria;
import app.models.CuentaBancariaMonedaExtranjera;
import app.models.CuentaBancariaMonedaNacional;
import app.models.MonedaExtranjera;
import app.services.interfaces.ServicioCuenta;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ServicioCuentaImpl implements ServicioCuenta {

	@NonNull
	ClienteDao clienteDao;
	@NonNull
	CuentaBancariaDao cuentaBancariaDao;

	@Override
	@Transactional
	public void agregarCotitular(Long idCliente, Long idCuenta) throws Exception {
		Cliente cliente = clienteDao.findById(idCliente).orElse(null);
		CuentaBancaria cuentaBancaria = cuentaBancariaDao.findById(idCuenta).orElse(null);

		// Las entidades en referencia deben existir
		if (cliente == null) {
			throw new IllegalArgumentException("El cliente en referencia no existe.");
		}
		if (cuentaBancaria == null) {
			throw new IllegalArgumentException("La cuenta en referencia no existe.");
		}

		// La cuenta debe estar abierta para realizar esta operación
		if (cuentaBancaria.getFechaCierre() != null) {
			throw new IllegalArgumentException("La cuenta se encuentra cerrada.");
		}

		// El cliente no debe ser titular o cotitular de la cuenta
		if (cuentaBancaria.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("El cliente ya es titular o cotitular de esta cuenta.");
		}

		cuentaBancaria.addCotitularidad(cliente);
	}

	@Override
	@Transactional
	public List<CuentaBancaria> listarCuentas() {
		return (List<CuentaBancaria>) cuentaBancariaDao.findAll();
	}

	@Override
	@Transactional
	public CuentaBancaria crearCuenta(Long idCliente, Double saldoInicial, String moneda) {
		Cliente cliente = clienteDao.findById(idCliente).orElse(null);

		// La entidad en referencia debe existir
		if (cliente == null) {
			throw new IllegalArgumentException("El cliente en referencia no existe.");
		}

		CuentaBancaria cuentaBancaria;
		if (moneda == null) {
			cuentaBancaria = crearCuentaMonedaNacional(cliente, saldoInicial);
		} else {
			cuentaBancaria = crearCuentaMonedaExtranjera(cliente, saldoInicial, moneda);
		}

		if (cuentaBancariaDao.save(cuentaBancaria)) {
			return cuentaBancaria;
		}

		return null;
	}

	private Long generarNroCuentaDesdeCliente(Cliente cliente) {
		return Long.valueOf(String.valueOf(cliente.getId()) + "000"
				+ String.valueOf(cliente.getCuentasTitular().size() + 1) + "000");
	}

	private CuentaBancariaMonedaNacional crearCuentaMonedaNacional(Cliente cliente, Double saldoInicial) {
		return new CuentaBancariaMonedaNacional(generarNroCuentaDesdeCliente(cliente), LocalDate.now(), saldoInicial,
				saldoInicial, 0.0, null, cliente);
	}

	private CuentaBancariaMonedaExtranjera crearCuentaMonedaExtranjera(Cliente cliente, Double saldoInicial,
			String moneda) {
		MonedaExtranjera monedaExtranjera = MonedaExtranjera.valueOf(moneda);
		return new CuentaBancariaMonedaExtranjera(generarNroCuentaDesdeCliente(cliente), LocalDate.now(), saldoInicial,
				saldoInicial, 0.0, null, cliente, monedaExtranjera);

	}

}
