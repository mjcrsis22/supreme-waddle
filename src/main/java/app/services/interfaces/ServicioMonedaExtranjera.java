package app.services.interfaces;

public interface ServicioMonedaExtranjera {
	public void VenderMonedaExtranjera(Long idCliente, Long idCuentaMonedaExtranjera, Long idCuentaMonedaNacional,
			Double monto);
}
