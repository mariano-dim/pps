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
import edu.proyectofinal.integradorrs.repositorys.TweetsRepository;
import edu.proyectofinal.integradorrs.services.facebook.FaceService;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.json.DataObjectFactory;
import org.apache.catalina.WebResource;
import org.springframework.http.MediaType;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Post;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Emi
 */
@Service
public class FaceServiceImpl implements FaceService {

    //@Autowired
    //private FaceRepository repository;

    @Override
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
    public ResponseList <facebook4j.Post> getUserTimeline(String user, String email) {
        ResponseList<facebook4j.Post> result = null;
        facebook4j.Facebook facebook = FacebookCredentials.getInstance().SetCredentials(email);
        try {
            
            result = facebook.getFeed(user);
        } catch (FacebookException ex) {
            Logger.getLogger(FaceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    @Override
    public String Post(String email, String texto) {
        String result = "Inicio de envío.";
        facebook4j.Facebook facebook = FacebookCredentials.getInstance().SetCredentials(email);
        try {
            facebook.postStatusMessage(texto);
            result = "Post realizado satisfactoriamente.";
        } catch (FacebookException ex) {
            result = ex.getErrorMessage();
            Logger.getLogger(FaceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }



    
}
