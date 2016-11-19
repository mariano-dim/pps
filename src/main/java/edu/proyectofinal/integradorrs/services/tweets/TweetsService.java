package edu.proyectofinal.integradorrs.services.tweets;

import java.util.Collection;
import twitter4j.Status;


public interface TweetsService {
    
    	Collection<Status> getAllTweets(String email);

        Collection<Status> getUserTimeline(String user, String email);

        public void Post(String email, String texto);
    
}

