package edu.proyectofinal.integradorrs.repositorys;

import java.util.Date;

public interface UsuarioRepositoryCustom {

	int updateUsuario(String email, String newEmail, String nombre, String clave);
        
        int updateUsuario(String email, String newEmail, String nombre, String clave, int pond_likes, int pond_comments, int pond_shares);
}
