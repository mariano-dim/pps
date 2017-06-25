package edu.proyectofinal.integradorrs.services.usuario;

import edu.proyectofinal.integradorrs.model.Token;
import java.util.Collection;

import edu.proyectofinal.integradorrs.model.TokenEmail;
import edu.proyectofinal.integradorrs.model.Usuario;

public interface UsuarioService {

	Collection<Usuario> getAllUsuarios();

	Collection<Token> getAllTokens();

	Usuario getByEmail(String email);

	// Collection<Token> getTokenByEmail(String email);
	Token getTokenByEmail(String email);

	public Token getTokenByEmailAndSN(String email, String rs);

	Usuario getById(String id);

	Usuario save(Usuario t);

	Token saveToken(Token t);

	// Token deleteToken(Token t);
	Token deleteToken(String email, String rs);
	
	Usuario patch(Usuario usuariop, String email);

	TokenEmail recoveryPasswordGenerateToken(String email);

	TokenEmail getByEmailAndExpired(String email);

	void deletePrevToken(TokenEmail tokenEmail);

	TokenEmail getByEmailAndToken(String email, String token);

	void deleteEmailToken(TokenEmail tokenEmail);

	boolean isExpirateToken(TokenEmail tokenEmail);

}