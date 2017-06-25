package edu.proyectofinal.integradorrs.exceptions;

/**
 * Created by mdimaggio on 25/06/17.
 */
public class InvalidTokenException extends AbstractApiRestException {
    public InvalidTokenException(Class<?> clazz){
        super("Token Invalido: " + clazz.getName());
    }
}
