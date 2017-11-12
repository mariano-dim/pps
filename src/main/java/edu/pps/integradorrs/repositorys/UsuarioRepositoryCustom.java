package edu.pps.integradorrs.repositorys;

import java.util.Date;

public interface UsuarioRepositoryCustom {

	int updateUsuario(String email, String newEmail, String nombre);
        
}
