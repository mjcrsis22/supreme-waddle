package app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	// TODO: Clientes: Debe permitir listar a todos los clientes, buscar por nombre,
	// obtener un cliente por id, y cambiar la dirección.

	@GetMapping("/saludo")
	public String holaMundo() {
		return "Hola mundo!!";
	}

}
