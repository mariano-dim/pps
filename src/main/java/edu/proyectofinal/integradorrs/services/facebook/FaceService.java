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
import twitter4j.Status;

/**
 *
 * @author Emi
 */
public interface FaceService {

    ResponseList <facebook4j.Post> getAllPost(String email);

    ResponseList <facebook4j.Post> getUserTimeline(String email);
    
    Collection <Update> getUserTimelinev2(String email);
    
    public String Post(String email, String texto);
    
    Post GetById(String id, String email);
    
    Update GetByIdv2(String id, String email);
    
    Collection<Update> ToUpdateCollection(Collection<facebook4j.Post> aCollection);
    
    ResponseList<Post> GetByMultiplesId(List<String> ids, String email);
    
    Update saveUpdate(Update update);

    public int GetFollowers(String email);
    
    public int GetFollows(String email);
    
}
