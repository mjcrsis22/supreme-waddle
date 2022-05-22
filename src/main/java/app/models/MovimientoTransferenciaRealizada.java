package app.models;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity(name = "T_MOVIMIENTO_TRANSFERENCIA_REALIZADA")
@DiscriminatorValue(value = "MOV_TR_REALIZADA")
public class MovimientoTransferenciaRealizada extends Movimiento {
	@ManyToOne(optional = false)
	@JoinColumn(updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private CuentaBancaria cuentaDestino;

	public MovimientoTransferenciaRealizada() {
		super();
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

	// TODO: Metodos especiales
	@Override
	public boolean ejecutar(CuentaBancaria cuentaBancaria) throws Exception {
		return cuentaBancaria.aplicarMovimiento(this);
	}

}
