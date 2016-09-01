package edu.proyectofinal.integradorrs.services.usuario.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.repositorys.UsuarioRepository;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;

@Service("UsuarioServiceMongo")
public class UsuarioServiceMongo implements UsuarioService{
	
	
    @Autowired
    private UsuarioRepository repository;
    
    public Collection<Usuario> getAll(){
        Collection<Usuario> result = repository.findAll();
        return result;
    }
    @Override
    public Usuario getById(String id) {
        return repository.findOne(id);
    }
    
    @Override
    public Usuario getByUsuario(String usuario) {
        return repository.findOne(usuario);
    }

    public Usuario save(Usuario t){
        return repository.insert(t);
    }

}
