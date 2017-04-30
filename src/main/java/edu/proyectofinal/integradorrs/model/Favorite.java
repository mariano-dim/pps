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
public class Favorite {
    
    @Id
    private String id;
    @Field
    private Date since;
    @Field
    private String email;
    @Field
    private String socialnetwork;
    @Field
    private String id_update;
    
    public Favorite (String email, String socialnetwork, String id_update)
    {
        this.since = new Date();
        this.email = email;
        this.socialnetwork = socialnetwork;
        this.id_update = id_update;
    }
    public Date getSince() {
        return since;
    }

    public String getEmail() {
        return email;
    }

    public String getSocialnetwork() {
        return socialnetwork;
    }

    public String getId_update() {
        return id_update;
    }


}
