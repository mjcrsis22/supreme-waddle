package app.services.interfaces;

public interface ServicioMonedaExtranjera {
	public void venderMonedaExtranjera(Long idCliente, Long idCuentaMonedaExtranjera, Long idCuentaMonedaNacional,
			Double monto) throws Exception;
}
