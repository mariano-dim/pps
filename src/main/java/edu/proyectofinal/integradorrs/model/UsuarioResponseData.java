/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.proyectofinal.integradorrs.model.get.singular.UsuarioResponse;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author MarianoAndres
 */
public class UsuarioResponseData implements UsuarioResponse {

    @Field
    private String id;

    public UsuarioResponseData() {
        super();
    }

    public UsuarioResponseData(String id) {
        super();
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setID(String publicID) {
        this.id = publicID;
    }

    @Override
    @JsonIgnore
    public boolean isAnErrorResponse() {
        return false;
    }
}
