package app.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity(name = "T_MOVIMIENTO_EXTRACCION")
@DiscriminatorValue(value = "MOV_EXTRACCION")
public class MovimientoExtraccion extends Movimiento {
	@Column(nullable = false, updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private String cajero;

	public MovimientoExtraccion() {
		super();
	}

	public MovimientoExtraccion(LocalDateTime fechaHora, Double monto, String descripcion, String cajero) {
		super(fechaHora, monto, descripcion);
		this.cajero = cajero;
	}

	public String getCajero() {
		return cajero;
	}

	public void setCajero(String cajero) {
		this.cajero = cajero;
	}

	// TODO: Metodos especiales
	@Override
	public boolean ejecutar(CuentaBancaria cuentaBancaria) throws Exception {
		return cuentaBancaria.aplicarMovimiento(this);
	}

}
