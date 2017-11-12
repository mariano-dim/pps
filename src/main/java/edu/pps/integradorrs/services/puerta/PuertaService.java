package edu.pps.integradorrs.services.puerta;

import java.util.Collection;

import edu.pps.integradorrs.model.Puerta;

public interface PuertaService {

      Collection<Puerta> getAllPuertas();

//    Usuario getByEmail(String email);

  //  Usuario getById(String id);

      Puerta save(Puerta t);

 //   void patch(Usuario usuariop, String email);

}