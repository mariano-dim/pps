package edu.proyectofinal.integradorrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.exceptions.EmptyResultException;
import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class LoginController extends AbstractController<Usuario> {

	@Autowired
	private UsuarioService usuarioService;

	
	@RequestMapping(method = RequestMethod.GET, value = "/usuario/{usuario}")
	public ResponseEntity<Usuario> getTxByUsuario(@PathVariable("usuario") String usuario) {

		System.out.println("getByUsuario");
		System.out.println("Usuario: " + usuario);
		
		Usuario result = usuarioService.getByUsuario(usuario);
		if (result == null) {
			throw new EmptyResultException(Usuario.class);
		}
		return super.singleResult(result);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
	public ResponseEntity<Usuario> getTxById(@PathVariable("id") String id) {

		System.out.println("getById");
		System.out.println("Id: " + id);
		Usuario result = usuarioService.getById(id);
		if (result == null) {
			throw new EmptyResultException(Usuario.class);
		}
		return super.singleResult(result);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> save(@ModelAttribute Usuario t) {

		Usuario result = usuarioService.save(t);
		return super.createdResult(result);
	}
}
