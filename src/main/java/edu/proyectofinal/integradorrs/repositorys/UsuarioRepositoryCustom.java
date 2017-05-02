package edu.proyectofinal.integradorrs.repositorys;

import java.util.Date;

public interface UsuarioRepositoryCustom {

	int updateUsuario(String email, String clave, String calle, String cuidad, Date fechaNacimiento, String pais,
			String provincia, String telefono);
}
