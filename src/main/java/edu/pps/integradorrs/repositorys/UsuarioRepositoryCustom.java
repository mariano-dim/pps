package edu.pps.integradorrs.repositorys;

import java.util.Date;

public interface UsuarioRepositoryCustom {

	int updateUsuario(String email, String newEmail, String nombre);

	int addLlave(String usuarioEmail, String llavepublicIdentification);

	int removeLlave(String usuarioEmail, String llavepublicIdentification);
        
}
