package app.dtos.implementations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCuentaVenderMonedaExtranjera {
	private Long idCliente;
	private Long idCuentaMonedaExtranjera;
	private Long idCuentaMonedaNacional;
	private Double monto;
}
