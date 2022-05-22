package app.services.implementations;

import java.time.LocalDate;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.ClienteDao;
import app.daos.interfaces.CuentaBancariaDao;
import app.models.Cliente;
import app.models.CuentaBancaria;
import app.models.CuentaBancariaMonedaExtranjera;
import app.models.CuentaBancariaMonedaNacional;
import app.models.Direccion;
import app.models.MonedaExtranjera;
import app.services.interfaces.ServicioCuenta;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServicioCuentaImpl implements ServicioCuenta {

	ClienteDao clienteDao;
	CuentaBancariaDao cuentaBancariaDao;

	@Override
	@Transactional
	public void loadData() {
		Direccion d = new Direccion("Calle 1", "123", "C", "5", "CABA", "1414", "BA");
		Cliente c1 = new Cliente("Danilo Jose", "Guerrero", d, "1123234545", "dguerrero@minibank.com");
		Cliente c2 = new Cliente("Maria", "Guerrero", d, "1167678989", "mguerrero@minibank.com");
		CuentaBancaria cb1 = new CuentaBancariaMonedaNacional(1L, LocalDate.now(), 200.0, 200.0, 100.0, null, c1);
		CuentaBancaria cb2 = new CuentaBancariaMonedaNacional(2L, LocalDate.now(), 0.0, 0.0, 100.0, null, c2);
		CuentaBancaria cb3 = new CuentaBancariaMonedaExtranjera(3L, LocalDate.now(), 100.0, 100.0, 50.0, null, c1,
				MonedaExtranjera.USD);

		clienteDao.save(c1);
		clienteDao.save(c2);
		cuentaBancariaDao.save(cb1);
		cuentaBancariaDao.save(cb2);
		cuentaBancariaDao.save(cb3);
	}

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

		clienteDao.update(cliente);
		cuentaBancariaDao.update(cuentaBancaria);
	}

}
