package app.services.interfaces;

import java.util.List;

import app.models.CuentaBancaria;

public interface ServicioCuenta {
	public void agregarCotitular(Long idCliente, Long idCuenta) throws Exception;

	public List<CuentaBancaria> listarCuentas();

	public CuentaBancaria crearCuenta(Long idCliente, Double saldoInicial, String moneda);
}
