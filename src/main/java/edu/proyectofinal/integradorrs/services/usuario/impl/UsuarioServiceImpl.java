package edu.proyectofinal.integradorrs.services.usuario.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.repositorys.UsuarioRepository;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
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

}

