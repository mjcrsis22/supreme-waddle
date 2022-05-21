package app.services.interfaces;

public interface ServicioCuenta {
	public void loadData();
	public void agregarCotitular(Long idCliente, Long idCuenta) throws Exception;
}
