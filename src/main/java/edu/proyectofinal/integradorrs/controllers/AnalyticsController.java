package edu.proyectofinal.integradorrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.model.Usuario;
import edu.proyectofinal.integradorrs.services.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import edu.proyectofinal.integradorrs.exceptions.EmptyResultException;
import edu.proyectofinal.integradorrs.model.Token;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.analytics.AnalyticsService;
import java.util.Collection;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



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
    
}
