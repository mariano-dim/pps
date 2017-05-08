/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.thymeleaf.util.DateUtils;

/**
 *
 * @author SocialFocus
 */
public class UpdateHistory implements Comparable<UpdateHistory> {
    
    @Field
    private String email;
    @Field
    private String socialnetwork;
    @Field
    private Date creationDate;

   
    @Field
    private Date measurementDate;
    @Field
    private String texto;
    @Field
    private String id;
    @Field
    private String post;
    @Field
    private int likes;
    @Field
    private int shares;
    @Field
    private int comments;

     public Date getMeasurementDate() {
        return measurementDate;
    }
    
    public String getid() {
        return post;
    }
    
     public int getlikes() {
        return likes;
    }
     
     public int getshares() {
        return shares;
    }
     
     public int getcomments() {
        return comments;
    }
     
     public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param email the email to set
     */
    public void setid(String id) {
        this.post = id;
    }
    
     public void setcreationdate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public void setlikes(int likes) {
        this.likes = likes;
    }
    
    public void setshares(int shares) {
        this.shares = shares;
    }
    
    public void setcomments(int comments) {
        this.comments = comments;
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
    public int compareTo(UpdateHistory o) {
        return this.getCreationDate().toInstant().compareTo(o.getCreationDate().toInstant());
    }
    
    public UpdateHistory()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -3);
        this.measurementDate = cal.getTime();
    }
        
}
