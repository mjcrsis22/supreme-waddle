package app.daos.interfaces;

import java.util.Collection;

import app.models.Cliente;

public interface ClienteDao extends BaseDao<Cliente> {
	Collection<Cliente> findByName(String nombreCliente);
}
