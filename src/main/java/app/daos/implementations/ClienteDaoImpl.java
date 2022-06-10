package app.daos.implementations;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import app.daos.interfaces.ClienteDao;
import app.models.Cliente;

public class ClienteDaoImpl extends BaseDaoImpl<Cliente> implements ClienteDao {

	public ClienteDaoImpl(EntityManager em) {
		super(em);
	}

	@Override
	protected Class<Cliente> getTypeClass() {
		return Cliente.class;
	}

	@Override
	protected boolean isCreated(Cliente cliente) {
		return cliente.getId() != null;
	}

	@Override
	protected String getFindAllNamedQuery() {
		return Cliente.findAllNamedQuery;
	}

	@Override
	public Collection<Cliente> findByName(String nombreCliente) {
		TypedQuery<Cliente> query = em.createNamedQuery(Cliente.findByNameNamedQuery, Cliente.class);
		query.setParameter("nombreCliente", nombreCliente);
		return query.getResultList();
	}

}
