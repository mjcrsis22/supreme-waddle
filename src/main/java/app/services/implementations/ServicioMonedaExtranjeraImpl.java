package app.services.implementations;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.ClienteDao;
import app.daos.interfaces.CuentaBancariaDao;
import app.daos.interfaces.MovimientoDao;
import app.dtos.interfaces.ResultadoCambio;
import app.models.Cliente;
import app.models.CuentaBancaria;
import app.models.MovimientoVentaMonedaExtranjera;
import app.services.interfaces.ServicioCambio;
import app.services.interfaces.ServicioMonedaExtranjera;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServicioMonedaExtranjeraImpl implements ServicioMonedaExtranjera {

	ClienteDao clienteDao;
	CuentaBancariaDao cuentaBancariaDao;
	MovimientoDao movimientoDao;
	ServicioCambio servicioCambio;

	@Override
	@Transactional
	public void venderMonedaExtranjera(Long idCliente, Long idCuentaMonedaExtranjera, Long idCuentaMonedaNacional,
			Double monto) throws Exception {
		Cliente cliente = clienteDao.findById(idCliente).orElse(null);
		CuentaBancaria cuentaMonedaExtranjera = cuentaBancariaDao.findById(idCuentaMonedaExtranjera).orElse(null);
		CuentaBancaria cuentaMonedaNacional = cuentaBancariaDao.findById(idCuentaMonedaNacional).orElse(null);

		// Las entidades en referencia deben existir
		if (cliente == null) {
			throw new IllegalArgumentException("El cliente en referencia no existe.");
		}
		if (cuentaMonedaExtranjera == null || !cuentaMonedaExtranjera.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("La cuenta en moneda extranjera en referencia no existe.");
		}
		if (cuentaMonedaNacional == null || !cuentaMonedaNacional.clienteEsTitularOCotitular(cliente)) {
			throw new IllegalArgumentException("La cuenta en moneda nacional en referencia no existe.");
		}

		// Las cuentas deben estar abiertas para realizar esta operación
		if (cuentaMonedaExtranjera.getFechaCierre() != null) {
			throw new IllegalArgumentException("La cuenta en moneda extranjera se encuentra cerrada.");
		}
		if (cuentaMonedaNacional.getFechaCierre() != null) {
			throw new IllegalArgumentException("La cuenta en moneda nacional se encuentra cerrada.");
		}

		// El cambio solo se realiza entre cuentas con diferente moneda
		if (cuentaMonedaExtranjera.getMoneda() == cuentaMonedaNacional.getMoneda()) {
			throw new IllegalArgumentException("Las cuentas tienen la misma moneda.");
		}

		// La cuenta en moneda extranjera debe tener saldo suficiente
		if (cuentaMonedaExtranjera.getSaldoActual() < monto) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		ResultadoCambio resultadoCambio = servicioCambio.cambiar(cuentaMonedaExtranjera.getMoneda(),
				cuentaMonedaNacional.getMoneda(), monto);

		MovimientoVentaMonedaExtranjera mov1 = new MovimientoVentaMonedaExtranjera(LocalDateTime.now(), monto,
				"Debito en cuenta por venta de moneda extranjera.", resultadoCambio.getTasa(), 0.00);
		MovimientoVentaMonedaExtranjera mov2 = new MovimientoVentaMonedaExtranjera(LocalDateTime.now(),
				resultadoCambio.getResultado(), "Acreditación en cuenta por venta de moneda extranjera.",
				resultadoCambio.getTasa(), 0.00);

		cuentaMonedaExtranjera.addMovimiento(mov1);
		cuentaMonedaNacional.addMovimiento(mov2);

		movimientoDao.save(mov1);
		movimientoDao.save(mov2);
	}

}
