package app.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity(name = "T_MOVIMIENTO_COMPRA_MONEDAEXTRANJERA")
public class MovimientoCompraMonedaExtranjera extends Movimiento {
	@Column(nullable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Positive(message = "{common.generic.positive}")
	private Double cotizacion;
	@Column(nullable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@PositiveOrZero(message = "{common.generic.positiveOrZero}")
	private Double comisionAplicada;

	public MovimientoCompraMonedaExtranjera() {
		super();
	}

	public MovimientoCompraMonedaExtranjera(LocalDateTime fechaHora, Double monto, String descripcion) {
		super(fechaHora, monto, descripcion);
	}

	public MovimientoCompraMonedaExtranjera(LocalDateTime fechaHora, Double monto, String descripcion,
			Double cotizacion, Double comisionAplicada) {
		super(fechaHora, monto, descripcion);
		this.cotizacion = cotizacion;
		this.comisionAplicada = comisionAplicada;
	}

	public Double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Double getComisionAplicada() {
		return comisionAplicada;
	}

	public void setComisionAplicada(Double comisionAplicada) {
		this.comisionAplicada = comisionAplicada;
	}

}
