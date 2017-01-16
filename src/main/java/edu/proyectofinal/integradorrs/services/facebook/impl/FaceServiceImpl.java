/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.facebook.impl;

import ch.qos.logback.core.net.server.Client;
import edu.proyectofinal.integradorrs.configurations.FacebookCredentials;
import edu.proyectofinal.integradorrs.services.tweets.impl.*;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.configurations.TwitterCredentials;
import edu.proyectofinal.integradorrs.model.Post;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.repositorys.TokenRepository;
import edu.proyectofinal.integradorrs.repositorys.TweetsRepository;
import edu.proyectofinal.integradorrs.services.facebook.FaceService;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.api.FriendMethods;
import facebook4j.auth.AccessToken;
import facebook4j.json.DataObjectFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.catalina.WebResource;
import org.springframework.http.MediaType;
import org.springframework.social.facebook.api.Facebook;
import edu.proyectofinal.integradorrs.repositorys.UpdatesRepository;


/**
 *
 * @author Emi
 */
@Service
public class FaceServiceImpl implements FaceService {

    //@Autowired
    //private FaceRepository repository;
    @Autowired
    private UpdatesRepository updatesrepository;
    
    @Override
    //@SuppressWarnings("empty-statement")
    public ResponseList <facebook4j.Post> getAllPost(String email) {
        ResponseList<facebook4j.Post> result = null;
        facebook4j.Facebook facebook = FacebookCredentials.getInstance().SetCredentials(email);
        try {
            result = facebook.getHome();
        } catch (FacebookException ex) {
            Logger.getLogger(FaceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    @Override
    public ResponseList <facebook4j.Post> getUserTimeline(String email) {
        ResponseList<facebook4j.Post> result = null;
        facebook4j.Facebook facebook = FacebookCredentials.getInstance().SetCredentials(email);
        try {
            result = facebook.getPosts();
        } catch (FacebookException ex) {
            System.out.println(ex);
           //Logger.getLogger(FaceServiceImpl.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return result;
    }

    @Override
    public String Post(String email, String texto) {
        String result = "Inicio de envío.";
        String id = "vacío";
        Update anUpdate = new Update();
        ResponseList<facebook4j.Post> posteos = null;
        facebook4j.Facebook facebook = FacebookCredentials.getInstance().SetCredentials(email);
        try {
            facebook.postStatusMessage(texto);
            result = "Post realizado satisfactoriamente.";
            posteos = facebook.getPosts();
            id = posteos.get(0).getId();
            anUpdate.setid(id);
            anUpdate.setEmail(email);
            anUpdate.setSocialnetwork("Facebook");
            anUpdate.setTexto(texto);
            this.saveUpdate(anUpdate);
        } catch (Exception ex) {
            result = ex.getMessage();
            Logger.getLogger(FaceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public Update saveUpdate(Update update) {
        updatesrepository.save(update);
        return update;
    }

    @Override
    public facebook4j.Post GetById(String id, String email) {
        facebook4j.Facebook facebook = FacebookCredentials.getInstance().SetCredentials(email);
        facebook4j.Post aPost = null;
        try {
            aPost = facebook.getPost(id);
        } catch (FacebookException ex) {
            Logger.getLogger(FaceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aPost;
    }
}
