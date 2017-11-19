package edu.pps.integradorrs.controllers;

import com.google.common.base.Strings;
import edu.pps.integradorrs.services.Email.EmailServiceSocialFocus;

import org.apache.commons.lang3.ArrayUtils;
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
@RequestMapping("/api/llave")
public class LlaveController extends AbstractController<Llave> {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LlaveService llaveService;

	@Autowired
	private PuertaService puertaService;

	@Autowired
	public EmailServiceSocialFocus emailService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<Collection<Llave>> getAllLlaves() {

		System.out.println("getAllLlaves");

		Collection<Llave> llaves = llaveService.getAllLlaves();

		return super.collectionResult(llaves);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Llave> save(@RequestBody Llave llave) {

		System.out.println("save llave");
        // Verifico si la nueva llave posee puertas asignadas
		if (null == llave.getPuertas()){
			System.out.println("Registrando nueva llave sin puertas asociadas");
		}else {
			// Verifico que la/las puertas asociadas existan, sino doy error
			// Busco la Puerta, a traves de su identificacion publica, en caso de no
			// encontrarla doy error
			for(String puerta: llave.getPuertas()) {
				// TODO: retornar la excepcion correcta BusinessException
				if (null == puertaService.getByPublicIdentification(puerta)) {
					System.out.println("Puerta no encontrada");
					throw new EmptyResultException(Puerta.class);				}			
			}
		}
				
		llaveService.save(llave);

		return new ResponseEntity<Llave>(llave, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/publicIdentification/{publicIdentification}")
	public ResponseEntity<Llave> getByPublicIdentification(
			@Validated @PathVariable("publicIdentification") String publicIdentification) {

		System.out.println("getByPublicIdentification");
		System.out.println("publicIdentification: " + publicIdentification);

		Llave llave = llaveService.getByPublicIdentification(publicIdentification);

		if (null == llave) {
			throw new EmptyResultException(Puerta.class);
		}
		return super.singleResult(llave);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/addpuertatollave/puerta/{puertapublicidentification}/llave/{llavepublicidentification}")
	public ResponseEntity<Llave> addPuertaToKey(
			@Validated @PathVariable("puertapublicidentification") String puertapublicidentification,
			@Validated @PathVariable("llavepublicidentification") String llavepublicidentification) {

		System.out.println("addPuertaToKey");
		System.out.println("puertapublicidentification: " + puertapublicidentification);
		System.out.println("llavepublicidentification: " + llavepublicidentification);

		// Busco la llave, en caso de no encontrarlo doy error
		Llave llave = llaveService.getByPublicIdentification(llavepublicidentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llave) {
			System.out.println("Llave no encontrada");
			throw new EmptyResultException(Llave.class);
		}

		// Busco la Puerta, a traves de su identificacion publica, en caso de no
		// encontrarla doy error
		Puerta puerta = puertaService.getByPublicIdentification(puertapublicidentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == puerta) {
			System.out.println("Puerta no encontrada");
			throw new EmptyResultException(Puerta.class);
		}
		// verifico si la llave ya tiene esa puerta asignada, en cuyo caso retorno
		if (ArrayUtils.contains(llave.getPuertas(), puertapublicidentification)) {
			System.out.println("La llave ya posee esa puerta asignada");
			return new ResponseEntity<Llave>(llave, HttpStatus.OK);
		}

		// Le agrego la Puerta a la llave
		llaveService.addPuerta(llavepublicidentification, puertapublicidentification);

		// Busco la llave, para retornar la informacion que se persistio en la db
		Llave llaveUpdated = llaveService.getByPublicIdentification(llavepublicidentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llaveUpdated) {
			throw new EmptyResultException(Llave.class);
		}
		return new ResponseEntity<Llave>(llaveUpdated, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/removepuertatollave/puerta/{puertapublicidentification}/llave/{llavepublicidentification}")
	public ResponseEntity<Llave> removepuertatollave(
			@Validated @PathVariable("puertapublicidentification") String puertapublicidentification,
			@Validated @PathVariable("llavepublicidentification") String llavepublicidentification) {

		System.out.println("removepuertatollave");
		System.out.println("puertapublicidentification: " + puertapublicidentification);
		System.out.println("llavepublicidentification: " + llavepublicidentification);

		// Busco la llave, en caso de no encontrarlo doy error
		Llave llave = llaveService.getByPublicIdentification(llavepublicidentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llave) {
			System.out.println("Llave no encontrada");
			throw new EmptyResultException(Llave.class);
		}

		// Busco la Puerta, a traves de su identificacion publica, en caso de no
		// encontrarla doy error
		Puerta puerta = puertaService.getByPublicIdentification(puertapublicidentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == puerta) {
			System.out.println("Puerta no encontrada");
			throw new EmptyResultException(Puerta.class);
		}

		// verifico si la llave ya tiene esa puerta asignada, en cuyo caso retorno
		if (!ArrayUtils.contains(llave.getPuertas(), puertapublicidentification)) {
			System.out.println("La llave indicada no posee esa puerta");
			return new ResponseEntity<Llave>(llave, HttpStatus.OK);
		}

		// Le borro la puerta a la llave
		llaveService.removePuerta(llavepublicidentification, puertapublicidentification);

		// Busco la llave, para retornar la informacion que se persistio en la db
		Llave llaveUpdated = llaveService.getByPublicIdentification(llavepublicidentification);
		// TODO: retornar la excepcion correcta BusinessException
		if (null == llaveUpdated) {
			throw new EmptyResultException(Llave.class);
		}
		return new ResponseEntity<Llave>(llaveUpdated, HttpStatus.OK);

	}
}
