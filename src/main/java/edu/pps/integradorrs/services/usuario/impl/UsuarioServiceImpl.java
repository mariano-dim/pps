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

		/**
		 * El Id, la fecha de creacion, y la clase no se deben poder modificar
		 * 
		 */
		/* JSON Example
		{
			"_id" : ObjectId("5951931fa312043524f9f58b"),
				"_class" : "Llave",
				"email" : "a@hotmial.com",
				"nombre" : "a",
				"clave" : "31a3952e9b1675d9dc36f1f9af1a083b38b7c476",
				"creationDate" : ISODate("2017-06-26T23:05:03.280Z")
		}
        */

		repository.updateUsuario(email, usuarioNewData.getEmail(), usuarioNewData.getNombre());

	}



}
