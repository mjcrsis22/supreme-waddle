package app.models;

import java.time.LocalDateTime;

public class MovimientoTransferenciaRecibida extends Movimiento {
	private CuentaBancaria cuentaOrigen;

	public MovimientoTransferenciaRecibida() {
		super();
	}

	public MovimientoTransferenciaRecibida(LocalDateTime fechaHora, Double monto, String descripcion) {
		super(fechaHora, monto, descripcion);
	}

	public MovimientoTransferenciaRecibida(LocalDateTime fechaHora, Double monto, String descripcion,
			CuentaBancaria cuentaOrigen) {
		super(fechaHora, monto, descripcion);
		this.cuentaOrigen = cuentaOrigen;
	}

	public CuentaBancaria getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

}
