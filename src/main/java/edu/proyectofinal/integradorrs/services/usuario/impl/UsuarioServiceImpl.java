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
	public void patch(Usuario usuarioNewData, String email) {

		/**
		 * El Id, la fecha de creacion, y la clase no se deben poder modificar
		 * 
		 */
		/* JSON Example
		{
			"_id" : ObjectId("5951931fa312043524f9f58b"),
				"_class" : "edu.proyectofinal.integradorrs.model.Usuario",
				"email" : "a@hotmial.com",
				"nombre" : "a",
				"clave" : "31a3952e9b1675d9dc36f1f9af1a083b38b7c476",
				"creationDate" : ISODate("2017-06-26T23:05:03.280Z")
		}
        */

		repository.updateUsuario(email, usuarioNewData.getEmail(), usuarioNewData.getNombre(), usuarioNewData.getClave());

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

	@Override
	public boolean isExpirateToken(TokenEmail tokenEmail){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
        // Si HOY es mayor que la fecha de expiracion del token (cuando se genero mas un dia x defecto)
		// entornce retorno true, para decir que el token no es valido
		if(calendar.getTime().after(tokenEmail.getTokenExpiration())) return true;
		else return false;

	}

}
