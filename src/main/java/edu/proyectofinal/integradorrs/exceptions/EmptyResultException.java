package edu.proyectofinal.integradorrs.exceptions;

import edu.proyectofinal.integradorrs.model.AbstractModel;

/**
 * Created by mariano on 25/03/16.
 */
public class EmptyResultException extends AbstractApiRestException {

    public EmptyResultException(Class<? extends AbstractModel> clazz){
        super("Reource not found: " + clazz.getName());
    }
}
