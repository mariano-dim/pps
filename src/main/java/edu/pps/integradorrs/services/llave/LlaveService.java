package edu.pps.integradorrs.services.llave;

import java.util.Collection;

import edu.pps.integradorrs.model.Llave;

public interface LlaveService {

    Collection<Llave> getAllLlaves();

//    Usuario getByEmail(String email);

  //  Usuario getById(String id);

    Llave save(Llave t);

//    void patch(Usuario usuariop, String email);

}