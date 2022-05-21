package app.dtos.implementations;

import app.dtos.interfaces.ResultadoTransferencia;
import app.models.MovimientoTransferenciaRealizada;
import app.models.MovimientoTransferenciaRecibida;
import lombok.Data;
import lombok.NonNull;

@Data
public class ResultadoTransferenciaImpl implements ResultadoTransferencia {
	@NonNull
	MovimientoTransferenciaRealizada movimientoTransferenciaRealizada;
	@NonNull
	MovimientoTransferenciaRecibida movimientoTransferenciaRecibida;
}
