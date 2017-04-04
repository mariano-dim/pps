package edu.proyectofinal.integradorrs.services.usuario.impl;

import edu.proyectofinal.integradorrs.model.Token;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.repositorys.TokenRepository;
import edu.proyectofinal.integradorrs.repositorys.UsuarioRepository;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
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

        usuario.setCreationDate(new Date());
        repository.save(usuario);
        return usuario;
    }

    
     //public Collection<Token> getTokenByEmail(String email) {
    //Collection<Token> result = (Collection<Token>) tokenrepository.findAll();
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
    @Override
    public Token deleteToken(Token t) {
        Date aDate = new Date();
        t.setCreationDate(aDate);
        t.setModDate(aDate);
        tokenrepository.delete(t);
        return t;
    }*/
    
      @Override
    public Token deleteToken(String email, String rs) {
        //Token t = tokenrepository.findByEmail(email);
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

}

