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
public class FollowersHistory {    

    @Field
    private Date fecharegistro;
    @Field
    private String email;
    @Field
    private String socialnetwork;
    @Field
    private int followers;
    
    public FollowersHistory(String email, String socialnetwork, int followers) {
        this.fecharegistro = new Date();
        this.email = email;
        this.socialnetwork = socialnetwork;
        this.followers = followers;
    }
    
    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialnetwork() {
        return socialnetwork;
    }

    public void setSocialnetwork(String socialnetwork) {
        this.socialnetwork = socialnetwork;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    
    }