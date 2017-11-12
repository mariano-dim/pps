package edu.pps.integradorrs.controllers;

import com.google.common.base.Strings;
import edu.pps.integradorrs.services.Email.EmailServiceSocialFocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.pps.integradorrs.model.Usuario;
import edu.pps.integradorrs.model.Llave;
import edu.pps.integradorrs.model.Puerta;

import edu.pps.integradorrs.services.usuario.UsuarioService;
import edu.pps.integradorrs.services.llave.LlaveService;
import edu.pps.integradorrs.services.puerta.PuertaService;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import edu.pps.integradorrs.exceptions.EmptyResultException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@RestController
@RequestMapping("/api/puerta")
public class PuertaController extends AbstractController<Puerta> {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LlaveService llaveService;

    @Autowired
    private PuertaService puertaService;

    @Autowired
    public EmailServiceSocialFocus emailService;


    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<Collection<Puerta>> getAllPuertas() {

        System.out.println("getAllPuertas");

        Collection<Puerta> puertas = puertaService.getAllPuertas();

        return super.collectionResult(puertas);

    }



    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Puerta> save(@RequestBody Puerta puerta) {

        puertaService.save(puerta);

        return new ResponseEntity<Puerta>(puerta, HttpStatus.OK);

    }




}


