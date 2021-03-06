package app.models;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "T_MOVIMIENTO_TRANSFERENCIA_RECIBIDA")
@DiscriminatorValue(value = "MOV_TR_RECIBIDA")
public class MovimientoTransferenciaRecibida extends Movimiento {
	@ManyToOne(optional = false)
	@JoinColumn(updatable = false)
	@NotNull(message = "{common.generic.notEmpty}")
	private CuentaBancaria cuentaOrigen;

	public MovimientoTransferenciaRecibida() {
		super();
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

	// TODO: Metodos especiales
	@Override
	public boolean ejecutar(CuentaBancaria cuentaBancaria) throws Exception {
		return cuentaBancaria.aplicarMovimiento(this);
	}

}
