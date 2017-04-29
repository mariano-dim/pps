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
public class UnifiedUpdate implements Comparable<UnifiedUpdate> {
    
    @Field
    private String email;
    @Field
    private String socialnetwork;
    @Field
    private String id_tw;
    @Field
    private String id_fb;
    @Field
    private Date creationDate;
    @Field
    private String texto;
    @Field
    private String id;
    @Field
    private int likes_fb;

    public int getLikes_fb() {
        return likes_fb;
    }

    public void setLikes_fb(int likes_fb) {
        this.likes_fb = likes_fb;
    }

    public int getShares_fb() {
        return shares_fb;
    }

    public void setShares_fb(int shares_fb) {
        this.shares_fb = shares_fb;
    }
    
    public String getid_fb() {
        return id_fb;
    }

    public void setid_fb(String id_fb) {
        this.id_fb = id_fb;
    }
    
    public String getid_tw() {
        return id_tw;
    }

    public void setid_tw(String id_tw) {
        this.id_tw = id_tw;
    }

    public int getComments_fb() {
        return comments_fb;
    }

    public void setComments_fb(int comments_fb) {
        this.comments_fb = comments_fb;
    }

    public int getLikes_tw() {
        return likes_tw;
    }

    public void setLikes_tw(int likes_tw) {
        this.likes_tw = likes_tw;
    }

    public int getShares_tw() {
        return shares_tw;
    }

    public void setShares_tw(int shares_tw) {
        this.shares_tw = shares_tw;
    }

    public int getComments_tw() {
        return comments_tw;
    }

    public void setComments_tw(int comments_tw) {
        this.comments_tw = comments_tw;
    }
    @Field
    private int shares_fb;
    @Field
    private int comments_fb;
     @Field
    private int likes_tw;
    @Field
    private int shares_tw;
    @Field
    private int comments_tw;

    public String getid() {
        return id;
    }
    
     
     public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param email the email to set
     */
    public void setid(String id) {
        this.id = id;
    }
    
     public void setcreationdate(Date creationDate) {
        this.creationDate = creationDate;
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

    @Override
    public int compareTo(UnifiedUpdate o) {
        return -1 * this.getCreationDate().toInstant().compareTo(o.getCreationDate().toInstant());
    }
}
