package app.dtos.interfaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import app.dtos.deserializers.ResultadoCambioDeserializer;

@JsonDeserialize(using = ResultadoCambioDeserializer.class)
public interface ResultadoCambio {
	/**
	 * @return Tasa aplicada al cambio
	 */
	public Double getTasa();

	/**
	 * @return El resultado de la conversion
	 */
	public Double getResultado();

}
