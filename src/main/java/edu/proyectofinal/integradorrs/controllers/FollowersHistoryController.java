package edu.proyectofinal.integradorrs.controllers;

import edu.proyectofinal.integradorrs.model.Favorite;
import edu.proyectofinal.integradorrs.model.FollowersHistory;
import edu.proyectofinal.integradorrs.model.UnifiedUpdate;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.analytics.AnalyticsService;
import edu.proyectofinal.integradorrs.services.favorites.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import edu.proyectofinal.integradorrs.services.wall.WallService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/followers/history")
public class FollowersHistoryController extends AbstractController<FollowersHistory> {
    
    @Autowired
    private AnalyticsService analyticsService;

    @RequestMapping(method = RequestMethod.GET, value="/tw/{email:.+}")
    public ResponseEntity<Collection<FollowersHistory>> getFollowersTW(@Validated @PathVariable("email") String email) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Muro unificado de publicaciones desde Social Focus");
        System.out.println("Inicio: " + dateFormat.format(new Date()));

        Collection<FollowersHistory> updates = analyticsService.getFollowersHistory(email,"Twitter");
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(updates);
    }    
    
    @RequestMapping(method = RequestMethod.GET, value="/fb/{email:.+}")
    public ResponseEntity<Collection<FollowersHistory>> getFollowersFB(@Validated @PathVariable("email") String email) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Muro unificado de publicaciones desde Social Focus");
        System.out.println("Inicio: " + dateFormat.format(new Date()));

        Collection<FollowersHistory> updates = analyticsService.getFollowersHistory(email,"Facebook");
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(updates);
    }    
 
    
}
