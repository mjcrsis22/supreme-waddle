package app.services.implementations;

import app.dtos.interfaces.ResultadoCambio;
import app.models.Moneda;
import app.services.interfaces.ServicioCambio;

public class ServicioCambioImpl implements ServicioCambio {

	@Override
	public ResultadoCambio cambiar(Moneda de, Moneda a, Double monto) {
		return new ResultadoCambio() {

			@Override
			public Double getTasa() {
				return 200.0;
			}

			@Override
			public Double getResultado() {
				return monto * 200.0;
			}
		};
	}

}
