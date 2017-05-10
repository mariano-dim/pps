/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.analytics.impl;

import edu.proyectofinal.integradorrs.services.facebook.impl.*;
import ch.qos.logback.core.net.server.Client;
import edu.proyectofinal.integradorrs.Adapters.Adapter;
import edu.proyectofinal.integradorrs.configurations.FacebookCredentials;
import edu.proyectofinal.integradorrs.services.tweets.impl.*;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.configurations.TwitterCredentials;
import edu.proyectofinal.integradorrs.model.Dashboard;
import edu.proyectofinal.integradorrs.model.Favorite;
import edu.proyectofinal.integradorrs.model.FollowersHistory;
import edu.proyectofinal.integradorrs.model.Post;
import edu.proyectofinal.integradorrs.model.Token;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.model.UpdateHistory;
import edu.proyectofinal.integradorrs.repositorys.FollowersHistoryRepository;
import edu.proyectofinal.integradorrs.repositorys.TokenRepository;
import edu.proyectofinal.integradorrs.repositorys.TweetsRepository;
import edu.proyectofinal.integradorrs.repositorys.UpdatesHistoryRepository;
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
import edu.proyectofinal.integradorrs.services.favorites.FavoriteService;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
import java.util.ArrayList;
import java.util.Collections;
import twitter4j.Status;


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
    private UsuarioService usuarioservice;
    @Autowired
    private FaceService facebookservice;
    @Autowired
    private TweetsService twitterservice;
    @Autowired
    private FollowersHistoryRepository followershistoryrepository;
    @Autowired
    private UpdatesHistoryRepository updateshistoryrepository;
    @Autowired
    private FavoriteService favoriteService;

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
               resultUpdate.setcreationdate(aPost.getCreatedTime());
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
               resultUpdate.setcreationdate(aStatus.getCreatedAt());
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

    @Override
    public void SaveFollowers() {
        String email;
        String SocialNetwork;
        int Followers;
        Collection<Token> tokens = usuarioservice.getAllTokens();
        for(Token aToken: tokens)
        {
            if(aToken.getSocialnetwork().equals("Facebook"))
            {
                Followers = facebookservice.GetFollowers(aToken.getEmail());       
            }
            else
            {
                Followers = twitterservice.GetFollowers(aToken.getEmail());         
            }
            followershistoryrepository.save(new FollowersHistory(aToken.getEmail(),aToken.getSocialnetwork(),Followers));    
        }
    }

    @Override
    public void SaveUpdatesHistory() {
        String email;
        String SocialNetwork;
        Update anUpdate;
        Collection<Favorite> favorites = favoriteService.getAllFavorite();
        for(Favorite aFavorite: favorites)
        {
            if(aFavorite.getSocialnetwork().equals("Facebook"))
            {
                anUpdate = Adapter.FacebookPostToUpdate(aFavorite.getEmail(), (facebookservice.GetById(aFavorite.getId_update(), aFavorite.getEmail())));       
            }
            else
            {
                anUpdate = Adapter.TwitterStatusToUpdate(aFavorite.getEmail(), twitterservice.GetById(aFavorite.getId_update(), aFavorite.getEmail()));
            }
            UpdateHistory anUH = new UpdateHistory();
            anUH.setEmail(anUpdate.getEmail());
            anUH.setSocialnetwork(anUpdate.getSocialnetwork());
            anUH.setTexto(anUpdate.getTexto());
            anUH.setcreationdate(anUpdate.getCreationDate());
            anUH.setcomments(anUpdate.getcomments());
            anUH.setid(anUpdate.getid());
            anUH.setlikes(anUpdate.getlikes());
            anUH.setshares(anUpdate.getshares());
            updateshistoryrepository.save(anUH);
        }
    }

    @Override
    public Dashboard getDashboard(String email, Date Desde, Date Hasta) {
        Dashboard aDB = new Dashboard();
        Double stdrDouble = 0.00;
        
        aDB.setZ01(stdrDouble);
        aDB.setZ02(stdrDouble);
        aDB.setZ03(stdrDouble);
        aDB.setZ04(stdrDouble);
        aDB.setZ05(stdrDouble);
        
        return aDB;
    }
}
