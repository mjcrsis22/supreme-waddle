package app.daos.implementations;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import app.daos.interfaces.MovimientoDao;
import app.models.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao {

	EntityManager em;

	public MovimientoDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean save(Movimiento movimiento) {
		em.persist(movimiento);
		return movimiento.getId() != null;
	}

	@Override
	public Optional<Movimiento> findById(Long id) {
		return Optional.of(em.find(Movimiento.class, id));
	}

	@Override
	public Collection<Movimiento> findAll() {
		TypedQuery<Movimiento> query = em.createNamedQuery("findAllMovimientos", Movimiento.class);
		return query.getResultList();
	}

	@Override
	public void delete(Movimiento movimiento) {
		em.remove(movimiento);
	}

	@Override
	public void update(Movimiento movimiento) {
		em.merge(movimiento);
	}

}
