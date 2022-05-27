package app.daos.implementations;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;

import app.daos.interfaces.BaseDao;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	EntityManager em;

	public BaseDaoImpl(EntityManager em) {
		this.em = em;
	}

	protected abstract Class<T> getTypeClass();

	protected abstract boolean isCreated(T objeto);

	protected abstract String getFindAllNamedQuery();

	@Override
	public boolean save(T objeto) {
		em.persist(objeto);
		return this.isCreated(objeto);
	}

	@Override
	public Optional<T> findById(Long id) {
		return Optional.ofNullable(em.find(this.getTypeClass(), id));
	}

	@Override
	public Collection<T> findAll() {
		return em.createNamedQuery(this.getFindAllNamedQuery(), this.getTypeClass()).getResultList();
	}

	@Override
	public void delete(T objeto) {
		em.remove(objeto);
	}

	@Override
	public void update(T objeto) {
		em.merge(objeto);
	}

}
