package app.models;

import java.time.LocalDateTime;

public class MovimientoDepositoExtraccion extends Movimiento {
	private String cajero;

	public MovimientoDepositoExtraccion() {
		super();
	}

	public MovimientoDepositoExtraccion(LocalDateTime fechaHora, Double monto, String descripcion) {
		super(fechaHora, monto, descripcion);
	}

	public MovimientoDepositoExtraccion(LocalDateTime fechaHora, Double monto, String descripcion, String cajero) {
		super(fechaHora, monto, descripcion);
		this.cajero = cajero;
	}

	public String getCajero() {
		return cajero;
	}

	public void setCajero(String cajero) {
		this.cajero = cajero;
	}

}
