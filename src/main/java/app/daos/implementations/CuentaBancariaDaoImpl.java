package app.daos.implementations;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import app.daos.interfaces.CuentaBancariaDao;
import app.models.CuentaBancaria;

public class CuentaBancariaDaoImpl extends BaseDaoImpl<CuentaBancaria> implements CuentaBancariaDao {

	public CuentaBancariaDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	protected Class<CuentaBancaria> getTypeClass() {
		return CuentaBancaria.class;
	}

	@Override
	protected boolean isCreated(CuentaBancaria cuentaBancaria) {
		return cuentaBancaria.getId() != null;
	}

	@Override
	protected String getFindAllNamedQuery() {
		return CuentaBancaria.findAllNamedQuery;
	}

	@Override
	public Collection<CuentaBancaria> findByCurrency(String monedaAsociada) {
		TypedQuery<CuentaBancaria> query = em.createNamedQuery(CuentaBancaria.findByCurrencyNamedQuery,
				CuentaBancaria.class);
		query.setParameter("monedaAsociada", monedaAsociada);
		return query.getResultList();
	}

}
