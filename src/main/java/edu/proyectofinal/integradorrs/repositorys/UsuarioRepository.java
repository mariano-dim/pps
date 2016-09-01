package edu.proyectofinal.integradorrs.repositorys;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.proyectofinal.integradorrs.model.Usuario;;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	
    public Collection<Usuario> findByUsuario(String email);
    
}