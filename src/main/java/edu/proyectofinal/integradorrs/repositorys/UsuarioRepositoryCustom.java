package edu.proyectofinal.integradorrs.repositorys;

import java.util.Date;

public interface UsuarioRepositoryCustom {

	int updateUsuario(String email, String clave, String nombre);
}
