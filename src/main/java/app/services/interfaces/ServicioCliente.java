package app.services.interfaces;

import java.util.List;

import app.models.Cliente;
import app.models.Direccion;

public interface ServicioCliente {

	public List<Cliente> listarClientes();

	public List<Cliente> buscarClientesPorNombre(String nombre);

	public Cliente buscarClientePorId(Long idCliente);

	public void cambiarDireccion(Long idCliente, Direccion direccion);

}
