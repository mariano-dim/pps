/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.tweets.impl;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.configurations.TwitterCredentials;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.repositorys.TweetsRepository;
import edu.proyectofinal.integradorrs.repositorys.UpdatesRepository;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import java.util.ArrayList;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author MarianoAndres
 */
@Service
public class TweetsServiceImpl implements TweetsService {

    @Autowired
    private UpdatesRepository updatesrepository;

    @Override
    public Collection<Status> getAllTweets(String email) {
     
     TwitterCredentials tc = TwitterCredentials.getInstance();
     ConfigurationBuilder cb = tc.GetCredentials(email);
    
     TwitterFactory tf = new TwitterFactory(cb.build());
     Twitter twitter = tf.getInstance();
       List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<Status> result = (Collection<Status>)statuses;
        return result;

    }

    @Override
    public Collection<Status> getUserTimeline(String user, String email) {
     TwitterCredentials tc = TwitterCredentials.getInstance();
     ConfigurationBuilder cb = tc.GetCredentials(email);
    
     TwitterFactory tf = new TwitterFactory(cb.build());
     Twitter twitter = tf.getInstance();
     List<Status> statuses = null;    
     Paging paging = new Paging(1, 100);
        try {
            statuses = twitter.getUserTimeline(user, paging);
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      Collection<Status> result = (Collection<Status>)statuses;
      return result;
    }

    @Override
    public void Post(String email, String texto) {
     TwitterCredentials tc = TwitterCredentials.getInstance();
     ConfigurationBuilder cb = tc.GetCredentials(email);
     Update anUpdate = new Update();
     TwitterFactory tf = new TwitterFactory(cb.build());
     Twitter twitter = tf.getInstance();
        try {
            Status status = twitter.updateStatus(texto);
            anUpdate.setid(Long.toString(status.getId()));
            anUpdate.setEmail(email);
            anUpdate.setSocialnetwork("Twitter");
            anUpdate.setTexto(texto);
            this.saveUpdate(anUpdate);
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Update saveUpdate(Update update) {
        updatesrepository.save(update);
        return update;   
    }

    @Override
    public Status GetById(String Id, String email) {
     TwitterCredentials tc = TwitterCredentials.getInstance();
     ConfigurationBuilder cb = tc.GetCredentials(email);
     TwitterFactory tf = new TwitterFactory(cb.build());
     Twitter twitter = tf.getInstance();
     Status aStatus = null;
        try {
            aStatus = twitter.showStatus(Long.parseLong(Id));
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return aStatus;
    }

    @Override
    public int GetFollowers(String email) {
        TwitterCredentials tc = TwitterCredentials.getInstance();
        ConfigurationBuilder cb = tc.GetCredentials(email);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        int Followers = -1;
        try {
            Followers = twitter.friendsFollowers().getFollowersIDs(Long.MAX_VALUE).getIDs().length;
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Followers;
    }

    @Override
    public Collection<Status> getUserTimeline(String email) {
        TwitterCredentials tc = TwitterCredentials.getInstance();
     ConfigurationBuilder cb = tc.GetCredentials(email);
    
     TwitterFactory tf = new TwitterFactory(cb.build());
     Twitter twitter = tf.getInstance();
     List<Status> statuses = null;    
     Paging paging = new Paging(1, 50);
        try {
            statuses = twitter.getUserTimeline();
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      Collection<Status> result = (Collection<Status>)statuses;
      return result;
    }

    @Override
    public Collection<Update> ToUpdateCollection(Collection<Status> aCollection) {
        
       Collection<Update> aResult = null;

        return aResult;
        
    }

    @Override
    public int GetFollows(String email) {
        TwitterCredentials tc = TwitterCredentials.getInstance();
        ConfigurationBuilder cb = tc.GetCredentials(email);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        int Followers = -1;
        try {
            Followers = twitter.friendsFollowers().getFriendsIDs(Long.MAX_VALUE).getIDs().length;
        } catch (TwitterException ex) {
            Logger.getLogger(TweetsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Followers;
    }

    
}
