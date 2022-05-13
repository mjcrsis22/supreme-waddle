package app.models;

import java.time.LocalDateTime;

public class MovimientoTransferenciaRealizada extends Movimiento {
	private CuentaBancaria cuentaDestino;

	public MovimientoTransferenciaRealizada() {
		super();
	}

	public MovimientoTransferenciaRealizada(LocalDateTime fechaHora, Double monto, String descripcion) {
		super(fechaHora, monto, descripcion);
	}

	public MovimientoTransferenciaRealizada(LocalDateTime fechaHora, Double monto, String descripcion,
			CuentaBancaria cuentaDestino) {
		super(fechaHora, monto, descripcion);
		this.cuentaDestino = cuentaDestino;
	}

	public CuentaBancaria getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(CuentaBancaria cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

}
