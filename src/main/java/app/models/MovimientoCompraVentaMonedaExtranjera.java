package app.models;

import java.time.LocalDateTime;

public class MovimientoCompraVentaMonedaExtranjera extends Movimiento {
	private Double cotizacion;
	private Double comisionAplicada;

	public MovimientoCompraVentaMonedaExtranjera() {
		super();
	}

	public MovimientoCompraVentaMonedaExtranjera(LocalDateTime fechaHora, Double monto, String descripcion) {
		super(fechaHora, monto, descripcion);
	}

	public MovimientoCompraVentaMonedaExtranjera(LocalDateTime fechaHora, Double monto, String descripcion,
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
