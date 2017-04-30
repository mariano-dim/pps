package edu.proyectofinal.integradorrs.controllers;

import edu.proyectofinal.integradorrs.model.Favorite;
import edu.proyectofinal.integradorrs.model.UnifiedUpdate;
import edu.proyectofinal.integradorrs.model.Update;
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
@RequestMapping("/wall")
public class WallController extends AbstractController<UnifiedUpdate> {
    
    @Autowired
    private WallService wallService;
    @Autowired 
    private FavoriteService favService;

    @RequestMapping(method = RequestMethod.GET, value="/{email:.+}")
    public ResponseEntity<Collection<UnifiedUpdate>> getAll(@Validated @PathVariable("email") String email) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Muro unificado de publicaciones desde Social Focus");
        System.out.println("Inicio: " + dateFormat.format(new Date()));

        Collection<UnifiedUpdate> updates = wallService.getUnifiedWall(email);
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(updates);
    }    
    
    @RequestMapping(method = RequestMethod.GET, value="/savefavorites/{email:.+}")
    public ResponseEntity<Collection<UnifiedUpdate>> saveFavorites(@Validated @PathVariable("email") String email, String id_fb, String id_tw) {
        
        Collection<UnifiedUpdate> aResult = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Operación de guardado de favoritos");
        System.out.println("Inicio: " + dateFormat.format(new Date()));

        if(!id_fb.contains("-1"))
        {
            favService.save(new Favorite(email,"Facebook",id_fb));
        }
        if(!id_tw.contains("-1"))
        {
            favService.save(new Favorite(email,"Twitter",id_tw));
        }
        //Collection<UnifiedUpdate> updates = wallService.getUnifiedWall(email);
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(aResult);
    }
    
        @RequestMapping(method = RequestMethod.GET, value="/deletefavorites/{email:.+}")
        public ResponseEntity<Collection<UnifiedUpdate>> deleteFavorites(@Validated @PathVariable("email") String email, String id_fb, String id_tw) {
        
        Collection<UnifiedUpdate> aResult = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Operación de guardado de favoritos");
        System.out.println("Inicio: " + dateFormat.format(new Date()));

        if(id_fb != "-1")
        {
            favService.delete(email,id_fb);
        }
        if(id_tw != "-1")
        {
            favService.delete(email,id_tw);
        }
        //Collection<UnifiedUpdate> updates = wallService.getUnifiedWall(email);
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(aResult);
    }  
    
    
}
