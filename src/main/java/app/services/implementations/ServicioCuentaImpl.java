package app.services.implementations;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.ClienteDao;
import app.daos.interfaces.CuentaBancariaDao;
import app.models.Cliente;
import app.models.CuentaBancaria;
import app.services.interfaces.ServicioCuenta;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServicioCuentaImpl implements ServicioCuenta {

	ClienteDao clienteDao;
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

		clienteDao.update(cliente);
		cuentaBancariaDao.update(cuentaBancaria);
	}

}
