/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pps.integradorrs.model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author SocialFocus
 */
public class Dashboard {

    public static final DecimalFormat df = new DecimalFormat("0.00");

    @Field
    private Date creationDate;
    @Field
    private int followers_t;
    @Field
    private int followers_f;
    @Field
    private Double z01_t;
    @Field
    private Double z02_t;
    @Field
    private Double z03_t;
    @Field
    private Double z04_t;
    @Field
    private Double z05_t;
    @Field
    private Double z01_f;
    @Field
    private Double z02_f;
    @Field
    private Double z03_f;
    @Field
    private Double z04_f;
    @Field
    private Double z05_f;

    public Dashboard() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -3);
        this.creationDate= cal.getTime();
        this.followers_f = 0;
        this.followers_t = 0;
        this.z01_f = 0.00;
        this.z02_f = 0.00;
        this.z03_f = 0.00;
        this.z04_f = 0.00;
        this.z05_f = 0.00;
        this.z01_t = 0.00;
        this.z02_t = 0.00;
        this.z03_t = 0.00;
        this.z04_t = 0.00;
        this.z05_t = 0.00;
        
    }
    
    
    public int getFollowers_t() {
        return followers_t;
    }

    public void setFollowers_t(int followers_t) {
        this.followers_t = Math.abs(followers_t);
    }

    public int getFollowers_f() {
        return followers_f;
    }

    public void setFollowers_f(int followers_f) {
        this.followers_f = Math.abs(followers_f);
    }
    
    public Double getZ01_t() {
        return z01_t;
    }

    public void setZ01_t(Double z01_t) throws ParseException {
        this.z01_t = Math.abs(z01_t);
    }

    public Double getZ02_t() {
        return z02_t;
    }

    public void setZ02_t(Double z02_t) throws ParseException {
        this.z02_t = Math.abs(z02_t);
    }

    public Double getZ03_t() {
        return z03_t;
    }

    public void setZ03_t(Double z03_t) throws ParseException {
        this.z03_t = Math.abs(z03_t);
    }

    public Double getZ04_t() {
        return z04_t;
    }

    public void setZ04_t(Double z04_t) throws ParseException {
        this.z04_t = Math.abs(z04_t);
    }

    public Double getZ05_t() {
        return z05_t;
    }

    public void setZ05_t(Double z05_t) throws ParseException {
        this.z05_t = Math.abs(z05_t);
    }

    public Double getZ01_f() {
        return z01_f;
    }

    public void setZ01(Double z01_f) throws ParseException {
        this.z01_f = Math.abs(z01_f);
    }

    public Double getZ02_f() {
        return z02_f;
    }

    public void setZ02(Double z02_f) throws ParseException {
        this.z02_f = Math.abs(z02_f);
    }

    public Double getZ03_f() {
        return z03_f;
    }

    public void setZ03(Double z03_f) throws ParseException {
        this.z03_f = Math.abs(z03_f);
    }

    public Double getZ04_f() {
        return z04_f;
    }

    public void setZ04(Double z04_f) throws ParseException {
        this.z04_f = Math.abs(z04_f);
    }

    public Double getZ05_f() {
        return z05_f;
    }

    public void setZ05(Double z05_f) throws ParseException {
        this.z05_f = Math.abs(z05_f);
    }

    public void getZ03_f(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
