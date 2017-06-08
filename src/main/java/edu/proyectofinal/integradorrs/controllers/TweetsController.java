package edu.proyectofinal.integradorrs.controllers;


import edu.proyectofinal.integradorrs.Adapters.Adapter;
import edu.proyectofinal.integradorrs.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import twitter4j.Status;


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
    
    @RequestMapping(method = RequestMethod.GET, value="/SelectedUserTimeline/{user:.+}")
    public ResponseEntity<Collection<Status>> getUserTimelineDates(@Validated @PathVariable("user") String user, String email
    , String fechaDesde, String fechaHasta) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("getAll");
        Date desde = format.parse(fechaDesde.substring(6, 10) + "-" + fechaDesde.substring(3, 5)+ "-" + fechaDesde.substring(0, 2)+ " " + fechaDesde.substring(11, 16)+":00");
        Date hasta = format.parse(fechaHasta.substring(6, 10) + "-" + fechaHasta.substring(3, 5)+ "-" + fechaHasta.substring(0, 2)+ " " + fechaHasta.substring(11, 16)+":00");
        Collection<Status> Status = tweetsService.getUserTimeline(user, email);
        Status.removeIf(p-> (p.getCreatedAt().compareTo(desde) <0 || p.getCreatedAt().compareTo(hasta)>0));
        
        return super.collectionResult(Status);

    }
    
     @RequestMapping(method = RequestMethod.GET, value="/getbyid/{email:.+}")
    public ResponseEntity<Update> getByID(@Validated @PathVariable("email") String email,String id) {

        System.out.println("Obtener publicación propia por ID de post");
        System.out.println(email);

        Update Status = Adapter.TwitterStatusToUpdate(email, tweetsService.GetById(id, email));

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.POST, value="/Create")
    public ResponseEntity<Post> PostTweet(@RequestBody Post post) {

        System.out.println("Create Post");

        tweetsService.Post(post.getEmail(), post.getTexto());

        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

}
