package app.dtos.implementations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCuentaCrear {
	@NonNull
	private Long idCliente;
	@NonNull
	private Double saldoInicial;
	private String moneda;
}
