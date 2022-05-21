package app.services.interfaces;

public interface ServicioTransferencia {
	public void realizarTransferencia(Long idCuentaOriginante, Double monto, Long idCuentaDestino) throws Exception;
}
