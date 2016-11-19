/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author SocialFocus
 */
public class Post {
    

    @Field
    private String email;
    @Field
    private String socialnetwork;
    @Field
    private String texto;
    @Field
    private String result;

    public String getResult() {
        return result;
    }

    /**
     * @param email the email to set
     */
    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the socialnetwork
     */
    public String getSocialnetwork() {
        return socialnetwork;
    }

    /**
     * @param socialnetwork the socialnetwork to set
     */
    public void setSocialnetwork(String socialnetwork) {
        this.socialnetwork = socialnetwork;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
}
