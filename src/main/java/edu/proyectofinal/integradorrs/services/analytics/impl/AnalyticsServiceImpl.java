/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.analytics.impl;

import edu.proyectofinal.integradorrs.services.facebook.impl.*;
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
import edu.proyectofinal.integradorrs.services.analytics.AnalyticsService;
import java.util.ArrayList;
import twitter4j.Status;
import twitter4j.TwitterException;


/**
 *
 * @author Emi
 */
@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    //@Autowired
    //private FaceRepository repository;
    @Autowired
    private UpdatesRepository updatesrepository;
    @Autowired
    private FaceService facebookservice;
    @Autowired
    private TweetsService twitterservice;

    @Override
    @SuppressWarnings("empty-statement")
    public Collection<Update> getUpdatesReport(String email) {
        
       Collection<Update> userUpdates = updatesrepository.findByEmail(email);
       Collection<Update> resultUpdates = new ArrayList<Update>();
       Update resultUpdate;
       Boolean result = true;
       for(Update anUpdate: userUpdates)
       {
           resultUpdate = new Update();
           resultUpdate.setEmail(anUpdate.getEmail());
           resultUpdate.setSocialnetwork(anUpdate.getSocialnetwork());
           resultUpdate.setTexto(anUpdate.getTexto());
           resultUpdate.setid(anUpdate.getid());
           if(anUpdate.getSocialnetwork().equals("Facebook"))
           {
               facebook4j.Post aPost = facebookservice.GetById(anUpdate.getid(), email);
               resultUpdate.setlikes(aPost.getLikes().size());
               if(aPost.getSharesCount() != null)
               {
                    resultUpdate.setshares(aPost.getSharesCount());
               }
               resultUpdate.setcomments(aPost.getComments().size());
           }
           else
           {
               Status aStatus = twitterservice.GetById(anUpdate.getid(), email);
               resultUpdate.setlikes(aStatus.getFavoriteCount());
               resultUpdate.setshares(aStatus.getRetweetCount());
               resultUpdate.setcomments(aStatus.getContributors().length);
                        
           }
        try {
           result = resultUpdates.add(resultUpdate);
        } catch (Exception ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
        
        return resultUpdates;
    }
    
    
}
