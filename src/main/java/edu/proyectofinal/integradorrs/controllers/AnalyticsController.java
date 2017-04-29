package edu.proyectofinal.integradorrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.analytics.AnalyticsService;
import java.util.Collection;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController extends AbstractController<Update> {
    
    @Autowired
    private AnalyticsService analyticsService;

    @RequestMapping(method = RequestMethod.GET, value="/informe/email/{email:.+}")
    public ResponseEntity<Collection<Update>> getAll(@Validated @PathVariable("email") String email) {

        System.out.println("Informe de publicaciones desde Social Focus");

        Collection<Update> updates = analyticsService.getUpdatesReport(email);

        return super.collectionResult(updates);

    }
    
    @RequestMapping(method = RequestMethod.POST, value="/informe/historico/seguidores")
    public void save() {
        
        analyticsService.SaveFollowers();
    }
    
}
