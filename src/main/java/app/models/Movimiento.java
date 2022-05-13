package app.models;

import java.time.LocalDateTime;

public abstract class Movimiento {
	private LocalDateTime fechaHora;
	private Double monto;
	private String descripcion;

	public Movimiento() {
	}

	public Movimiento(LocalDateTime fechaHora, Double monto, String descripcion) {
		super();
		this.fechaHora = fechaHora;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
