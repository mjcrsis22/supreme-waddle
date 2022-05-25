package app.services.implementations;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import app.daos.interfaces.ClienteDao;
import app.models.Cliente;
import app.models.Direccion;
import app.services.interfaces.ServicioCliente;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ServicioClienteImpl implements ServicioCliente {

	@NonNull
	ClienteDao clienteDao;

	@Override
	@Transactional
	public List<Cliente> listarClientes() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public List<Cliente> buscarClientesPorNombre(String nombre) {
		return (List<Cliente>) clienteDao.findByName(nombre);
	}

	@Override
	@Transactional
	public Cliente buscarClientePorId(Long idCliente) {
		return clienteDao.findById(idCliente).orElse(null);
	}

	@Override
	@Transactional
	public void cambiarDireccion(Long idCliente, Direccion direccion) {
		Cliente cliente = clienteDao.findById(idCliente).orElse(null);

		// La entidad en referencia debe existir
		if (cliente == null) {
			throw new IllegalArgumentException("El cliente en referencia no existe.");
		}

		cliente.setDireccion(direccion);
	}

}
