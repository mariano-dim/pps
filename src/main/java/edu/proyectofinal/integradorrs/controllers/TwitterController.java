package edu.proyectofinal.integradorrs.controllers;


import java.util.Collection;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import twitter4j.Status;

/**
 *
 * @author MarianoAndres
 */
@RestController
@RequestMapping("/api/profile/twitter")
public class TwitterController extends AbstractController<Status> {
	
	

    @Autowired
    private TweetsService tweetsService;
    @Autowired
    private Twitter twitter;
    
    private ConnectionRepository connectionRepository;
    
    @Inject
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }
    @RequestMapping(method = RequestMethod.GET, value="/email/{email:.+}")
    public ResponseEntity<Collection<Status>> getTimeline(@Validated @PathVariable("email") String email) {

        

        Collection<Status> Status = tweetsService.getAllTweets(email);

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/UserTimeline/{user:.+}")
    public ResponseEntity<Collection<Status>> getUserTimeline(@Validated @PathVariable("user") String user, String email) {

        
        Collection<Status> Status = tweetsService.getUserTimeline(user, email);

        return super.collectionResult(Status);

    }
    
   
    @RequestMapping(value="/friends", method=RequestMethod.GET, produces="application/json")
    public String helloTwitter(Model model) {
        /*
    	if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends); */
        return "hello";
    }
    
    //parametro path es absoluto; parametro value es relativo al mapping principal de la clase
    
    @RequestMapping(path="/profile/refresh/twitter", method=RequestMethod.GET)
    //@RequestMapping(method=RequestMethod.GET,  produces="application/json" )
	public void refreshTwitter(@RequestParam(value="component", required=false, defaultValue="profile") String component, Model model) {
		
		switch (component) {
		
		// GET /profile/refresh/twitter?component=friends
		case ("friends"):
			break;
	
		}
		//aca usar la clase UsuarioImpl para verificar ID ...y para guardar en su repo?
		//chequear si para el usuario actual existe una collecion existente (por id)
		//si no existe crearla
		//si si existe impactar los cambios
		

	}
    

    @RequestMapping(path="/getprofile", method=RequestMethod.GET)
        	 public @ResponseBody String getprofile(Model model) {
            
        	
        	System.out.println(connectionRepository.findPrimaryConnection(Twitter.class));
            CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
            HashMap<Long, String> friendsIds = new HashMap<Long, String>();
            int i=0;
            int length = friends.size();
            while(i<length) {
            	 TwitterProfile friend_iteration = friends.get(i);
            	 friendsIds.put(friend_iteration.getId(), friend_iteration.getName());
            	i++;
            }
           
            return friendsIds.toString();
        }

}
