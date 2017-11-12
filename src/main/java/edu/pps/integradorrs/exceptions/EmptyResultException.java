package edu.pps.integradorrs.exceptions;

/**
 * Created by mariano on 25/03/16.
 */
public class EmptyResultException extends AbstractApiRestException {

    public EmptyResultException(Class<?> clazz){
        super("Reource not found: " + clazz.getName());
    }
}
