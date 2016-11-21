package edu.proyectofinal.integradorrs.services.usuario;

import edu.proyectofinal.integradorrs.model.Token;
import java.util.Collection;

import edu.proyectofinal.integradorrs.model.Usuario;

public interface UsuarioService {

	Collection<Usuario> getAllUsuarios();
	
	Usuario getByEmail(String email);
        
        //Collection<Token> getTokenByEmail(String email);
        Token getTokenByEmail(String email);
	
        public Token getTokenByEmailAndSN(String email, String rs);
        
	Usuario getById(String id);

	Usuario save(Usuario t);
        
        Token saveToken(Token t);
        
       // Token deleteToken(Token t);
        Token deleteToken(String email, String rs);
}