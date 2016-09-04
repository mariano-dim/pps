package edu.proyectofinal.integradorrs.services.usuario;

import java.util.Collection;

import edu.proyectofinal.integradorrs.model.Usuario;

public interface UsuarioService {

	Collection<Usuario> getAllUsuarios();
	
	Usuario getByEmail(String email);
	
	Usuario getById(String id);

	Usuario save(Usuario t);
}
