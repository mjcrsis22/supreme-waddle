package app.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity(name = "T_MOVIMIENTO_TRANSFERENCIA_REALIZADA")
public class MovimientoTransferenciaRealizada extends Movimiento {
	@ManyToOne(optional = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
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
