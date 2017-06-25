package edu.proyectofinal.integradorrs.services.usuario.impl;

import edu.proyectofinal.integradorrs.model.Token;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.model.TokenEmail;
import edu.proyectofinal.integradorrs.repositorys.TokenRepository;
import edu.proyectofinal.integradorrs.repositorys.TokenEmailRepository;

import edu.proyectofinal.integradorrs.repositorys.UsuarioRepository;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.util.SessionIdentifierGenerator;
import edu.proyectofinal.integradorrs.model.TokenEmail;

import java.util.Date;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private TokenRepository tokenrepository;
	@Autowired
	private TokenEmailRepository tokenEmailRepository;

	@Value("${token.expiration}")
	private String tokenExpiration;

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
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getCalle())) {
			usuario.setCalle(usuarioToUpdate.getCalle());
		}
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getCiudad())) {
			usuario.setCiudad(usuarioToUpdate.getCiudad());
		}
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getPais())) {
			usuario.setPais(usuarioToUpdate.getPais());
		}
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getProvincia())) {
			usuario.setProvincia(usuarioToUpdate.getProvincia());
		}
		if (!Strings.isNullOrEmpty(usuarioToUpdate.getTelefono())) {
			usuario.setTelefono(usuarioToUpdate.getTelefono());
		}
		if (usuarioToUpdate.getFechaNacimiento() != null ) {
			usuario.setFechaNacimiento(usuarioToUpdate.getFechaNacimiento());
		}
		repository.updateUsuario(usuario.getEmail(),usuario.getClave(), usuario.getCalle(), 
				                      usuario.getCiudad(), usuario.getFechaNacimiento(),
				                      usuario.getPais(), usuario.getProvincia(), usuario.getTelefono()	);

		return usuario;
	}


	@Override
	public TokenEmail recoveryPasswordGenerateToken(String email){


		// El usuario debe existir, ya que lo busque justo antes de llamar a este metodo
		// Por lo tanto no lo busco
		// Gerero un token
		SessionIdentifierGenerator sessionIdentifierGenerator = new SessionIdentifierGenerator();
		String token = sessionIdentifierGenerator.nextSessionId();

		// Peristo en la base de datos el token e informacion adicional
		TokenEmail tokenEmail = new TokenEmail();
		tokenEmail.setToken(token);
		tokenEmail.setTokenEmail(email);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		Integer daysExpirarionToken = Integer.parseInt(tokenExpiration);
		tokenEmail.setTokenEnabled(true);
		calendar.add(Calendar.DATE, daysExpirarionToken.intValue() ); // Adds 1 days

		tokenEmail.setTokenExpiration(calendar.getTime());
		tokenEmailRepository.save(tokenEmail);


		return tokenEmail;
	}


	@Override
	public TokenEmail getByEmailAndExpired(String email) {

		return tokenEmailRepository.findByEmailandEnabled(email, true);
	}

	@Override
	public void deletePrevToken(TokenEmail tokenEmail) {

		tokenEmailRepository.delete(tokenEmail);
	}

	@Override
	public TokenEmail getByEmailAndToken(String email, String token) {

		return tokenEmailRepository.findByEmailandToken(email, token);
	}


	@Override
	public void deleteEmailToken(TokenEmail tokenEmail){

		tokenEmailRepository.delete(tokenEmail);
	}

}
