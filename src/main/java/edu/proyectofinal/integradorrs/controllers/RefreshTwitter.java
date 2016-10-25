package edu.proyectofinal.integradorrs.controllers;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.services.usuario.impl.UsuarioServiceImpl;

@RestController
("/profile/refresh/twitter")

public class RefreshTwitter {
	
    private Twitter twitter;
    private ConnectionRepository connectionRepository;

	UsuarioServiceImpl usuarioservicio;
	
	//@RequestMapping(method=RequestMethod.GET,  produces="application/json")
	@RequestMapping(method=RequestMethod.GET,  produces="application/json")
	public @ResponseBody void refreshTwitter(@RequestParam(value="component", required=false, defaultValue="profile") String component, Model model) {
		
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
}
	

