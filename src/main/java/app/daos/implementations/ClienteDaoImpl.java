package app.daos.implementations;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import app.daos.interfaces.ClienteDao;
import app.models.Cliente;

public class ClienteDaoImpl implements ClienteDao {

	EntityManager em;

	public ClienteDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean save(Cliente cliente) {
		em.persist(cliente);
		return cliente.getId() != null;
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		return Optional.ofNullable(em.find(Cliente.class, id));
	}

	@Override
	public Collection<Cliente> findAll() {
		TypedQuery<Cliente> query = em.createNamedQuery("cliente.findAll", Cliente.class);
		return query.getResultList();
	}

	@Override
	public void delete(Cliente cliente) {
		em.remove(cliente);
	}

	@Override
	public void update(Cliente cliente) {
		em.merge(cliente);
	}

	@Override
	public Collection<Cliente> findByName(String nombreCliente) {
		TypedQuery<Cliente> query = em.createNamedQuery("cliente.findByName", Cliente.class);
		query.setParameter("nombreCliente", nombreCliente);
		return query.getResultList();
	}

}
