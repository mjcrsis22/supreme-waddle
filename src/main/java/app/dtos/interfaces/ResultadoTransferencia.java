package app.dtos.interfaces;

import app.models.MovimientoTransferenciaRealizada;
import app.models.MovimientoTransferenciaRecibida;

public interface ResultadoTransferencia {
	/**
	 * @return MovimientoTransferenciaRealizada
	 */
	public MovimientoTransferenciaRealizada getMovimientoTransferenciaRealizada();

	/**
	 * @return MovimientoTransferenciaRecibida
	 */
	public MovimientoTransferenciaRecibida getMovimientoTransferenciaRecibida();

}
