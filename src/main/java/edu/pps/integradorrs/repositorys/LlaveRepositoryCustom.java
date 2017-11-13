package edu.pps.integradorrs.repositorys;

import java.util.Date;

public interface LlaveRepositoryCustom {

  //  int updateUsuario(String email, String newEmail, String nombre);

	int addPuerta(String llavepublicIdentification, String puertapublicIdentification);
	
	int removePuerta(String llavepublicIdentification, String puertapublicIdentification);
}
