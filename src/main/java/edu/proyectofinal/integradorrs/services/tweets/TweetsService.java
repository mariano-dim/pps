package edu.proyectofinal.integradorrs.services.tweets;

import edu.proyectofinal.integradorrs.model.Update;
import java.util.Collection;
import twitter4j.Status;


public interface TweetsService {
    
    	Collection<Status> getAllTweets(String email);

        Collection<Status> getUserTimeline(String user, String email);
        
        Collection<Status> getUserTimeline(String email);
        
        Collection<Update> ToUpdateCollection(Collection<Status> aCollection);

        Status GetById (String Id, String email);
        
        public void Post(String email, String texto);
        
        Update saveUpdate(Update update);

    public int GetFollowers(String email);
    
    public int GetFollows(String email);    
}

