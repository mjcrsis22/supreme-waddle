package app.services.implementations;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.CuentaBancariaDao;
import app.daos.interfaces.MovimientoDao;
import app.models.CuentaBancaria;
import app.models.MovimientoTransferenciaRealizada;
import app.models.MovimientoTransferenciaRecibida;
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

		// Las entidades en referencia deben existir
		if (cuentaOriginante == null) {
			throw new IllegalArgumentException("La cuenta originante en referencia no existe.");
		}
		if (cuentaDestino == null) {
			throw new IllegalArgumentException("La cuenta destino en referencia no existe.");
		}

		// Las cuentas deben estar abiertas para realizar esta operación
		if (cuentaOriginante.getFechaCierre() != null) {
			throw new Exception("La cuenta originante se encuentra cerrada.");
		}
		if (cuentaDestino.getFechaCierre() != null) {
			throw new Exception("La cuenta destino se encuentra cerrada.");
		}

		// Por el momento solo se puede transferir a cuentas con la misma moneda
		if (cuentaOriginante.getMoneda() != cuentaDestino.getMoneda()) {
			throw new Exception("Por el momento solo se admiten transferencias entre cuentas con la misma moneda.");
		}

		// La cuenta originante debe tener saldo suficiente
		if ((cuentaOriginante.getSaldoActual() + cuentaOriginante.getdescubiertoAcordado()) < monto) {
			throw new Exception("Saldo insuficiente para realizar esta operacion.");
		}

		MovimientoTransferenciaRealizada mov1 = new MovimientoTransferenciaRealizada(LocalDateTime.now(), monto,
				"Debito en cuenta por transferencia realizada.", cuentaOriginante);
		MovimientoTransferenciaRecibida mov2 = new MovimientoTransferenciaRecibida(LocalDateTime.now(), monto,
				"Acreditación en cuenta por transferencia recibida.", cuentaDestino);

		cuentaOriginante.addMovimiento(mov1);
		cuentaDestino.addMovimiento(mov2);

		movimientoDao.save(mov1);
		movimientoDao.save(mov2);
		cuentaBancariaDao.update(cuentaOriginante);
		cuentaBancariaDao.update(cuentaDestino);
	}

}
