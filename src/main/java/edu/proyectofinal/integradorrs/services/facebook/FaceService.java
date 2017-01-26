/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.facebook;

import edu.proyectofinal.integradorrs.model.Update;
import facebook4j.Post;
import facebook4j.ResponseList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Emi
 */
public interface FaceService {

    ResponseList <facebook4j.Post> getAllPost(String email);

    ResponseList <facebook4j.Post> getUserTimeline(String email);

    public String Post(String email, String texto);
    
    Post GetById(String id, String email);
    
    ResponseList<Post> GetByMultiplesId(List<String> ids, String email);
    
    Update saveUpdate(Update update);
    
}
