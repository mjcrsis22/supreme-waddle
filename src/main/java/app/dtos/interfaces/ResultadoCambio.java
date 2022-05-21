package app.dtos.interfaces;

public interface ResultadoCambio {
	/**
	 * @return Tasa aplicada al cambio
	 */
	public Long getTasa();

	/**
	 * @return El resultado de la conversion
	 */
	public Long getResultado();

}