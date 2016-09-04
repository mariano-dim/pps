package edu.proyectofinal.integradorrs.services.usuario;

import java.util.Collection;

import edu.proyectofinal.integradorrs.model.Usuario;
import java.util.List;

public interface UsuarioService {

	Collection<Usuario> getAll();
	
	Usuario getByEmail(String email);
	
	Usuario getById(String id);

	Usuario save(Usuario t);
}
