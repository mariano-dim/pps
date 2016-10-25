package edu.proyectofinal.integradorrs.controllers;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.proyectofinal.integradorrs.exceptions.EmptyResultException;

@Controller
@RequestMapping("/profile/get/twitter")
public class GetTwitter {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

	

	private Model output2;

    @Inject
    public GetTwitter (Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

 
    @RequestMapping(method=RequestMethod.GET,  produces="application/json")
    	 public @ResponseBody String getTwitter(@RequestParam(value="component", required=false, defaultValue="profile") String component, Model model) {
        
    	String output = null;    	
    	switch (component) {
    	
    	
    	case ("friends"):
    		
    	//System.out.println(connectionRepository.findPrimaryConnection(Twitter.class));
    		
    	output = twitter.friendOperations().getFriends().toString();
    	/*
    	CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        output = new HashMap<Long, String>();
        int i=0;
        int length = friends.size();
        
        while(i<length){
        	 TwitterProfile friend_iteration = friends.get(i);
        	 output.put(friend_iteration.getId(), friend_iteration.getName());
        	i++;
        }
        	*/
        
        break;
        
    	case ("followers"):

            /*model.addAttribute(twitter.userOperations().getUserProfile());
            CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
            model.addAttribute("friends", friends);*/
    		output = twitter.friendOperations().getFollowerIds().toString();
    	break;
    	
    	case ("following"):
    		
    		output = twitter.friendOperations().getFriendIds().toString();
    	break;
    	
    	case ("profile"):
    		
     		
    		output = twitter.userOperations().getUserProfile().getExtraData().toString();
    	break;
    	
   
    	default:
    		output2.addAttribute("API Exception", twitter);
    		EmptyResultException exception = new EmptyResultException(component.getClass());
    		throw exception   ;
    		
       
        
    	}
    return output; 
    // output.asMap().toString();
    	
    }
   
}
