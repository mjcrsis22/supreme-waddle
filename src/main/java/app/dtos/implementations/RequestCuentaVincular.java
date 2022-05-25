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
public class RequestCuentaVincular {
	@NonNull
	private Long idCliente;
	@NonNull
	private Long idCuenta;
}
