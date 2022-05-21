package app.services.implementations;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.CuentaBancariaDao;
import app.daos.interfaces.MovimientoDao;
import app.dtos.interfaces.ResultadoTransferencia;
import app.models.CuentaBancaria;
import app.services.interfaces.ServicioTransferencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServicioTransferenciaImpl implements ServicioTransferencia {

	CuentaBancariaDao cuentaBancariaDao;
	MovimientoDao movimientoDao;

	@Override
	@Transactional
	public void realizarTransferencia(Long idCuentaOriginante, Double monto, Long idCuentaDestino) throws Exception {
		CuentaBancaria cuentaOriginante = cuentaBancariaDao.findById(idCuentaOriginante).orElse(null);
		CuentaBancaria cuentaDestino = cuentaBancariaDao.findById(idCuentaDestino).orElse(null);

		if (cuentaOriginante == null) {
			throw new IllegalArgumentException("La cuenta originante en referencia no existe.");
		}
		if (cuentaDestino == null) {
			throw new IllegalArgumentException("La cuenta destino en referencia no existe.");
		}

		ResultadoTransferencia resultadoTransferencia = cuentaOriginante.realizarTransferencia(monto, cuentaDestino);

		movimientoDao.save(resultadoTransferencia.getMovimientoTransferenciaRealizada());
		movimientoDao.save(resultadoTransferencia.getMovimientoTransferenciaRecibida());
		cuentaBancariaDao.update(cuentaOriginante);
		cuentaBancariaDao.update(cuentaDestino);
	}

}
