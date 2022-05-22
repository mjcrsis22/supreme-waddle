package app.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

@Entity(name = "T_MOVIMIENTO")
@DiscriminatorColumn(name = "disc", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "MOV")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Movimiento {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@PastOrPresent(message = "{common.generic.pastOrPresent}")
	private LocalDateTime fechaHora;
	@Column(nullable = false, updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	@Positive(message = "{common.generic.positive}")
	private Double monto;
	@Column(nullable = false, updatable = false)
	@NotEmpty(message = "{common.generic.notEmpty}")
	private String descripcion;

	public Movimiento() {
	}

	public Movimiento(LocalDateTime fechaHora, Double monto, String descripcion) {
		this.fechaHora = fechaHora;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	// TODO: Metodos especiales
	public abstract boolean ejecutar(CuentaBancaria cuentaBancaria) throws Exception;

}
