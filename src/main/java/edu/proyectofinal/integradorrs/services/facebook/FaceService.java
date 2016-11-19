/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.facebook;

import facebook4j.ResponseList;
import java.util.Collection;
import twitter4j.Status;

/**
 *
 * @author Emi
 */
public interface FaceService {

    ResponseList <facebook4j.Post> getAllPost(String email);

    ResponseList <facebook4j.Post> getUserTimeline(String user, String email);

    public String Post(String email, String texto);
    
}
