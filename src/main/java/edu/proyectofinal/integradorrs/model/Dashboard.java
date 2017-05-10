/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.model;

import java.util.Calendar;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author SocialFocus
 */
public class Dashboard {

    @Field
    private Date creationDate;
    @Field
    private Double z01;
    @Field
    private Double z02;
    @Field
    private Double z03;
    @Field
    private Double z04;
    @Field
    private Double z05;

    public Dashboard() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -3);
        this.creationDate= cal.getTime();
        this.z01 = 0.00;
        this.z02 = 0.00;
        this.z03 = 0.00;
        this.z04 = 0.00;
        this.z05 = 0.00;
        
    }

    public Double getZ01() {
        return z01;
    }

    public void setZ01(Double z01) {
        this.z01 = z01;
    }

    public Double getZ02() {
        return z02;
    }

    public void setZ02(Double z02) {
        this.z02 = z02;
    }

    public Double getZ03() {
        return z03;
    }

    public void setZ03(Double z03) {
        this.z03 = z03;
    }

    public Double getZ04() {
        return z04;
    }

    public void setZ04(Double z04) {
        this.z04 = z04;
    }

    public Double getZ05() {
        return z05;
    }

    public void setZ05(Double z05) {
        this.z05 = z05;
    }
    
    
    
    
}
