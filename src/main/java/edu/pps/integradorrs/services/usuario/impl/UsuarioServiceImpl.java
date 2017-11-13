package edu.pps.integradorrs.services.usuario.impl;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.pps.integradorrs.model.Usuario;

import edu.pps.integradorrs.repositorys.UsuarioRepository;
import edu.pps.integradorrs.services.usuario.UsuarioService;

import java.util.Date;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;


	@Override
	public Collection<Usuario> getAllUsuarios() {
		Collection<Usuario> result = (Collection<Usuario>) repository.findAll();
		return result;
	}

	@Override
	public Usuario getById(String id) {
		return repository.findOne(id);
	}

	@Override
	public Usuario getByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Usuario save(Usuario usuario) {

		usuario.setCreationDate(new Date());
		repository.save(usuario);
		return usuario;
	}



	@Override
	public void patch(Usuario usuarioNewData, String email) {

		repository.updateUsuario(email, usuarioNewData.getEmail(), usuarioNewData.getNombre());

	}




	@Override
	public void addLlave(String usuarioEmail, String llavepublicIdentification) {

		repository.addLlave(usuarioEmail, llavepublicIdentification);

	}


	@Override
	public void removeLlave(String usuarioEmail, String llavepublicIdentification) {

		repository.removeLlave(usuarioEmail, llavepublicIdentification);

	}

}
