package edu.proyectofinal.integradorrs.controllers;

import edu.proyectofinal.integradorrs.model.UnifiedUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.wall.WallService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/wall")
public class WallController extends AbstractController<UnifiedUpdate> {
    
    @Autowired
    private WallService wallService;

    @RequestMapping(method = RequestMethod.GET, value="/{email:.+}")
    public ResponseEntity<Collection<UnifiedUpdate>> getAll(@Validated @PathVariable("email") String email) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Muro unificado de publicaciones desde Social Focus");
        System.out.println("Inicio: " + dateFormat.format(new Date()));

        Collection<UnifiedUpdate> updates = wallService.getUnifiedWall(email);
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(updates);
    }    
}
