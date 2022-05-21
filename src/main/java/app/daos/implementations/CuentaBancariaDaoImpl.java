package app.daos.implementations;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import app.daos.interfaces.CuentaBancariaDao;
import app.models.CuentaBancaria;

@NamedQuery(name = "findAllCuentasBancarias", query = "SELECT * FROM T_CUENTABANCARIA")
@NamedQuery(name = "findByCurrencyCuentasBancarias", query = "SELECT * FROM T_CUENTABANCARIA C LEFT JOIN T_CUENTABANCARIA_MONEDAEXTRANJERA E  ON  E.ID = C.ID WHERE E.MONEDAASOCIADA = :monedaAsociada")
public class CuentaBancariaDaoImpl implements CuentaBancariaDao {

	EntityManager em;

	public CuentaBancariaDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean save(CuentaBancaria cuentaBancaria) {
		em.persist(cuentaBancaria);
		return cuentaBancaria.getId() != null;
	}

	@Override
	public Optional<CuentaBancaria> findById(Long id) {
		return Optional.ofNullable(em.find(CuentaBancaria.class, id));
	}

	@Override
	public Collection<CuentaBancaria> findAll() {
		TypedQuery<CuentaBancaria> query = em.createNamedQuery("findAllCuentasBancarias", CuentaBancaria.class);
		return query.getResultList();
	}

	@Override
	public void delete(CuentaBancaria cuentaBancaria) {
		em.remove(cuentaBancaria);
	}

	@Override
	public void update(CuentaBancaria cuentaBancaria) {
		em.merge(cuentaBancaria);
	}

	@Override
	public Collection<CuentaBancaria> findByCurrency(String monedaAsociada) {
		TypedQuery<CuentaBancaria> query = em.createNamedQuery("findByCurrencyCuentasBancarias", CuentaBancaria.class);
		query.setParameter("monedaAsociada", monedaAsociada);
		return query.getResultList();
	}

}
