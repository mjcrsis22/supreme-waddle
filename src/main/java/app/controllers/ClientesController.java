package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.dtos.implementations.RequestClienteCambiarDir;
import app.models.Cliente;
import app.services.interfaces.ServicioCliente;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	ServicioCliente servicioCliente;

	@GetMapping("/listar")
	public List<Cliente> listar() {
		return servicioCliente.listarClientes();
	}

	@GetMapping("/buscarPorNombre/{nombre}")
	public List<Cliente> buscarPorNombre(@PathVariable String nombre) {
		return servicioCliente.buscarClientesPorNombre(nombre);
	}

	@GetMapping("/buscarPorId/{idCliente}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long idCliente) {
		return new ResponseEntity<Cliente>(servicioCliente.buscarClientePorId(idCliente), HttpStatus.FOUND);
	}

	@PutMapping("/cambiarDireccion")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void cambiarDireccion(@RequestBody RequestClienteCambiarDir requestClienteCambiarDir) {
		servicioCliente.cambiarDireccion(requestClienteCambiarDir.getIdCliente(),
				requestClienteCambiarDir.getDireccion());
	}

}
