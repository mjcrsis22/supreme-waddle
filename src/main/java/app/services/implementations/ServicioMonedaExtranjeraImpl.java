package app.services.implementations;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.ClienteDao;
import app.daos.interfaces.CuentaBancariaDao;
import app.models.Cliente;
import app.models.CuentaBancaria;
import app.services.interfaces.ServicioMonedaExtranjera;

public class ServicioMonedaExtranjeraImpl implements ServicioMonedaExtranjera {

	ClienteDao clienteDao;
	CuentaBancariaDao cuentaBancariaDao;

	@Override
	@Transactional
	public void VenderMonedaExtranjera(Long idCliente, Long idCuentaMonedaExtranjera, Long idCuentaMonedaNacional,
			Double monto) {
		Cliente cliente = clienteDao.findById(idCliente).orElse(null);
		CuentaBancaria cuentaMonedaExtranjera = cuentaBancariaDao.findById(idCuentaMonedaExtranjera).orElse(null);
		CuentaBancaria cuentaMonedaNacional = cuentaBancariaDao.findById(idCuentaMonedaNacional).orElse(null);

		if (cliente == null) {
			throw new IllegalArgumentException("El cliente en referencia no existe.");
		}
		if (cuentaMonedaExtranjera == null || !cuentaMonedaExtranjera.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("La cuenta en moneda extranjera en referencia no existe.");
		}
		if (cuentaMonedaNacional == null || !cuentaMonedaNacional.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("La cuenta en moneda nacional en referencia no existe.");
		}
		
		// TODO: continuará...

	}

}
