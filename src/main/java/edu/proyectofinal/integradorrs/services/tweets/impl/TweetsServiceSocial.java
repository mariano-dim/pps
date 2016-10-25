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
import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.repositorys.TweetsRepository;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;



/**
 *
 * @author MarianoAndres
 */
@Service
public class TweetsServiceSocial  {

    @Autowired
    private TweetsRepository repository;
    private Usuario usuario;
    private Twitter twitter;


    
    public CursoredList<Long> getAllTweets(Usuario usuario) {
     
    	return twitter.friendOperations().getFollowerIds();
 
    }

   
    public CursoredList<Long> getUserTimeline(Usuario usuario) {
     
    	return twitter.friendOperations().getFriendIds();
    	//implementar clase usuario para twitter sea un atributo y entonces:
    	//this.usuario.getTwitter().getUserTimeline();
    }
    
    //De Julian
	
	public CursoredList<Long> getFollowers(Usuario usuario) {
		
		return twitter.friendOperations().getFollowerIds();
		
	}

    
}
