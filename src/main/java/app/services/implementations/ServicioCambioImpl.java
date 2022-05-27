package app.services.implementations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import app.dtos.interfaces.ResultadoCambio;
import app.models.Moneda;
import app.services.interfaces.ServicioCambio;

public class ServicioCambioImpl implements ServicioCambio {

	HttpEntity<Void> httpEntity;
	RestTemplate template;

	String baseUri = "https://api.apilayer.com/exchangerates_data/";
	String queryCambiar = "convert?to={to}&from={from}&amount={amount}";

	public ServicioCambioImpl(@Value("${apilayer.apiKey}") String apiKey) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", apiKey);
		httpEntity = new HttpEntity<>(headers);

		template = new RestTemplate();
	}

	@Override
	public ResultadoCambio cambiar(Moneda de, Moneda a, Double monto) {
		Map<String, String> variables = new HashMap<>();
		variables.put("from", de.toString());
		variables.put("to", a.toString());
		variables.put("amount", monto.toString());

		ResponseEntity<ResultadoCambio> respuesta = template.exchange(baseUri + queryCambiar, HttpMethod.GET,
				httpEntity, ResultadoCambio.class, variables);

		return respuesta.getBody();
	}

}
