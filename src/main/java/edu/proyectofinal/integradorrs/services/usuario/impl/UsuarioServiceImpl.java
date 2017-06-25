package edu.proyectofinal.integradorrs.services.usuario.impl;

import edu.proyectofinal.integradorrs.model.Token;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.repositorys.TokenRepository;
import edu.proyectofinal.integradorrs.repositorys.UsuarioRepository;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
import edu.proyectofinal.integradorrs.model.Usuario;
import java.util.Date;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private TokenRepository tokenrepository;

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
           // if (this.findByEmail(usuario.getEmail())){};
		usuario.setCreationDate(new Date());
		repository.save(usuario);
		return usuario;
	}

	// public Collection<Token> getTokenByEmail(String email) {
	// Collection<Token> result = (Collection<Token>) tokenrepository.findAll();
	@Override
	public Token getTokenByEmail(String email) {

		Token t = tokenrepository.findByEmail(email);
		return t;
	}

	@Override
	public Token getTokenByEmailAndSN(String email, String rs) {

		Token t = tokenrepository.findByEmailandSN(email, rs);
		return t;
	}

	@Override
	public Token saveToken(Token t) {
		Date aDate = new Date();
		t.setCreationDate(aDate);
		t.setModDate(aDate);
		tokenrepository.save(t);
		return t;
	}

	/*
	 * @Override public Token deleteToken(Token t) { Date aDate = new Date();
	 * t.setCreationDate(aDate); t.setModDate(aDate); tokenrepository.delete(t);
	 * return t; }
	 */

	@Override
	public Token deleteToken(String email, String rs) {
		// Token t = tokenrepository.findByEmail(email);
		Token t = tokenrepository.findByEmailandSN(email, rs);
		Date aDate = new Date();
		t.setCreationDate(aDate);
		t.setModDate(aDate);
		tokenrepository.delete(t);
		return t;
	}

	@Override
	public Collection<Token> getAllTokens() {
		return tokenrepository.findAll();
	}

	@Override
	public Usuario patch(Usuario usuarioToUpdate, String email) {

		// Busco el usuario pasandole el email
		Usuario usuario = getByEmail(email);

		// Conservo los datos del usuario anterior, para imprimir ambos valores
		// en stdout
		final Usuario prevUsuario = new Usuario(usuario);
		
		/**
		 * El Id, el email, la fecha de creacion, y la clase no se deben poder modificar
		 * 
		 */

		// Splo actualizo los campos que son enviados
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getClave())) {
			usuario.setClave(usuarioToUpdate.getClave());
		}
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getNombre())) {
			usuario.setNombre(usuarioToUpdate.getNombre());
		}
		
		repository.updateUsuario(usuario.getEmail(),usuario.getClave(), usuario.getNombre());

		return usuario;
	}

}
