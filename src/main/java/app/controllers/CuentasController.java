package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import app.dtos.implementations.RequestCuentaCrear;
import app.dtos.implementations.RequestCuentaTransferirMismoBanco;
import app.dtos.implementations.RequestCuentaVincular;
import app.models.CuentaBancaria;
import app.services.interfaces.ServicioCuenta;
import app.services.interfaces.ServicioTransferencia;

@RestController
@RequestMapping("/cuentas")
public class CuentasController {
	// TODO: Cuentas: Mostrar todas las cuentas, crear una nueva cuenta, agregar un
	// cliente como cotitular de una cuenta, realizar una transferencia a una cuenta
	// del mismo banco.

	@Autowired
	ServicioCuenta servicioCuenta;
	@Autowired
	ServicioTransferencia servicioTransferencia;

	@GetMapping("/listar")
	public List<CuentaBancaria> listar() {
		return servicioCuenta.listarCuentas();
	}

	@PostMapping("/crear") // , consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CuentaBancaria> crear(@RequestBody RequestCuentaCrear requestCuentaCrear) {
		try {
			CuentaBancaria cuentaBancaria = servicioCuenta.crearCuenta(requestCuentaCrear.getIdCliente(),
					requestCuentaCrear.getSaldoInicial(), requestCuentaCrear.getMoneda());

			if (cuentaBancaria != null) {
				return new ResponseEntity<>(cuentaBancaria, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return null;
	}

	@PostMapping("/agregarCotitular")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void agregarCotitular(@RequestBody RequestCuentaVincular requestCuentaVincular) {
		try {
			servicioCuenta.agregarCotitular(requestCuentaVincular.getIdCliente(), requestCuentaVincular.getIdCuenta());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/transferirMismoBanco")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void transferirMismoBanco(@RequestBody RequestCuentaTransferirMismoBanco requestCuentaTransferir) {
		try {
			servicioTransferencia.realizarTransferencia(requestCuentaTransferir.getIdCuentaOriginante(),
					requestCuentaTransferir.getMonto(), requestCuentaTransferir.getIdCuentaDestino());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
