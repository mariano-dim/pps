package edu.pps.integradorrs.services.usuario;

import java.util.Collection;

import edu.pps.integradorrs.model.Usuario;

public interface UsuarioService {

	Collection<Usuario> getAllUsuarios();

	Usuario getByEmail(String email);

	Usuario getById(String id);

	Usuario save(Usuario t);

	void patch(Usuario usuariop, String email);

}