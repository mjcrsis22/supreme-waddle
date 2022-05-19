package app.daos.interfaces;

import java.util.Collection;

import app.models.CuentaBancaria;

public interface CuentaBancariaDao extends BaseDao<CuentaBancaria> {
	Collection<CuentaBancaria> findByCurrency();
}
