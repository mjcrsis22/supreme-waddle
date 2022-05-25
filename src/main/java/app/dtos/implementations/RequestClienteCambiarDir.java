package app.dtos.implementations;

import app.models.Direccion;
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
public class RequestClienteCambiarDir {
	@NonNull
	private Long idCliente;
	@NonNull
	private Direccion direccion;
}
