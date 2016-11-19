package edu.proyectofinal.integradorrs.controllers;


import edu.proyectofinal.integradorrs.model.Post;
import edu.proyectofinal.integradorrs.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.model.TweetsModel;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import twitter4j.Status;

/**
 *
 * @author Emi
 */
@RestController
@CrossOrigin
@RequestMapping("/api/tweets")
public class TweetsController extends AbstractController<Status> {

    @Autowired
    private TweetsService tweetsService;
    

    @RequestMapping(method = RequestMethod.GET, value="/email/{email:.+}")
    public ResponseEntity<Collection<Status>> getTimeline(@Validated @PathVariable("email") String email) {

        System.out.println("getAll");

        Collection<Status> Status = tweetsService.getAllTweets(email);

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/UserTimeline/{user:.+}")
    public ResponseEntity<Collection<Status>> getUserTimeline(@Validated @PathVariable("user") String user, String email) {

        System.out.println("getAll");

        Collection<Status> Status = tweetsService.getUserTimeline(user, email);

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.POST, value="/Create")
    public ResponseEntity<Post> PostTweet(@RequestBody Post post) {

        System.out.println("Create Post");

        tweetsService.Post(post.getEmail(), post.getTexto());

        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

}
