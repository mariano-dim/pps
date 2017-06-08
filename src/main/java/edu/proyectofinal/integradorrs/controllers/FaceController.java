package edu.proyectofinal.integradorrs.controllers;


import edu.proyectofinal.integradorrs.Adapters.Adapter;
import edu.proyectofinal.integradorrs.model.Post;
import edu.proyectofinal.integradorrs.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.model.TweetsModel;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.facebook.FaceService;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import facebook4j.ResponseList;
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

/**
 *
 * @author Emi
 */
@RestController
@CrossOrigin
@RequestMapping("/api/face")
public class FaceController extends AbstractController<Status> {

    @Autowired
    private FaceService faceService;
    

    @RequestMapping(method = RequestMethod.GET, value="/Prueba")
    public ResponseEntity<ResponseList<facebook4j.Post>> getUserTimeline() {

        System.out.println("Obtener posteos propios");
        System.out.println("Prueba");

        Collection<Update> StatusP = faceService.getUserTimelinev2("damian@prueba.com");
        ResponseList<facebook4j.Post> Status = faceService.getAllPost("damian@prueba.com");

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/email/{email:.+}")
    public ResponseEntity<ResponseList<facebook4j.Post>> getTimeline(@Validated @PathVariable("email") String email) {

        System.out.println("Obtener muro");
        System.out.println(email);

        ResponseList<facebook4j.Post> Status = faceService.getAllPost(email);

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/UserTimeline/{email:.+}")
    public ResponseEntity<ResponseList<facebook4j.Post>> getUserTimeline(@Validated @PathVariable("email") String email) {

        System.out.println("Obtener posteos propios");
        System.out.println(email);

        ResponseList<facebook4j.Post> Status = faceService.getUserTimeline(email);

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/SelectedUserTimeline/{email:.+}")
    public ResponseEntity<ResponseList<facebook4j.Post>> getUserTimelineDates(@Validated @PathVariable("email") String email
    , String fechaDesde, String fechaHasta) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Obtener posteos propios");
        System.out.println(email);
        Date desde = format.parse(fechaDesde.substring(6, 10) + "-" + fechaDesde.substring(3, 5)+ "-" + fechaDesde.substring(0, 2)+ " " + fechaDesde.substring(11, 16)+":00");
        Date hasta = format.parse(fechaHasta.substring(6, 10) + "-" + fechaHasta.substring(3, 5)+ "-" + fechaHasta.substring(0, 2)+ " " + fechaHasta.substring(11, 16)+":00");
        
        ResponseList<facebook4j.Post> Status = faceService.getUserTimeline(email);
        Status.removeIf(p-> (p.getCreatedTime().compareTo(desde) <0 || p.getCreatedTime().compareTo(hasta)>0));
        
        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/getbyid/{email:.+}")
    public ResponseEntity<Update> getByID(@Validated @PathVariable("email") String email,String id) {

        System.out.println("Obtener publicación propia por ID de post");
        System.out.println(email);

        Update Status = Adapter.FacebookPostToUpdate(email, faceService.GetById(id, email));

        return super.collectionResult(Status);

    }
    
    @RequestMapping(method = RequestMethod.POST, value="/Create")
    public ResponseEntity<Post> PostTweet(@RequestBody Post post) {

        System.out.println("Create Post");

        String result = faceService.Post(post.getEmail(), post.getTexto());
        post.setResult(result);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

}
